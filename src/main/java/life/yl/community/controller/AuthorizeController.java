package life.yl.community.controller;

import life.yl.community.dto.AccessTokenDTO;
import life.yl.community.dto.GithubUser;
import life.yl.community.mapper.UserMapper;
import life.yl.community.model.User;
import life.yl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author yanglin
 * @create 2019-11-29 17:05
 */
@Controller
public class AuthorizeController {

  @Autowired
  private GithubProvider githubProvider;

  @Value("${github.client.id}")
  private String clientId;

  @Value("${github.client.secret}")
  private String clientSecret;

  @Value("${github.client.uri}")
  private String clientUri;

  @Autowired
  private UserMapper userMapper;

  /**
   * 获取code,和state,http://localhost:8887/callback?code=f2c9366110ed06771a84&state=1
   * @param code
   * @param state
   * @return
   */
  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state,
                         HttpServletRequest request){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(clientUri);
    accessTokenDTO.setState(state);
    accessTokenDTO.setClient_id(clientId);
    accessTokenDTO.setClient_secret(clientSecret);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.gitUser(accessToken);
    if(githubUser != null){
      User user = new User();
      user.setToken(UUID.randomUUID().toString());
      user.setName(githubUser.getName());
      user.setAccountId(String.valueOf(githubUser.getId()));
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insert(user);
      //登录成功，写cookie和session
      request.getSession().setAttribute("user", githubUser);
      return "redirect:/";
    }else {
      //登录失败，重新登录
      return "redirect:/";
    }
  }

}
