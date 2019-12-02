package life.yl.community.controller;

import life.yl.community.dto.AccessTokenDTO;
import life.yl.community.dto.GithubUser;
import life.yl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  /**
   * 获取code,和state,http://localhost:8887/callback?code=f2c9366110ed06771a84&state=1
   * @param code
   * @param state
   * @return
   */
  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(clientUri);
    accessTokenDTO.setState(state);
    accessTokenDTO.setClient_id(clientId);
    accessTokenDTO.setClient_secret(clientSecret);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser user = githubProvider.gitUser(accessToken);
    System.out.println(user.getName());
    return "index";
  }

}
