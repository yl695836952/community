package life.yl.community.controller;

import life.yl.community.dto.AccessTokenDTO;
import life.yl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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


  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
    accessTokenDTO.setState(state);
    accessTokenDTO.setClient_id("Iv1.1cc8ba2c98594a7c");
    accessTokenDTO.setClient_secret("59e0184cc1bf36ca2da35b3a4fdaf8f2f9a6fbba");
    githubProvider.getAccessToken(accessTokenDTO);
    return "index";
  }

}
