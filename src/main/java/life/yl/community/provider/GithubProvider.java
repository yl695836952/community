package life.yl.community.provider;

import com.alibaba.fastjson.JSON;
import life.yl.community.dto.AccessTokenDTO;
import life.yl.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Github提供者
 * @author yanglin
 * @create 2019-11-29 17:13
 */
@Component
public class GithubProvider {

  /**
   * 获取token
   * 调用https://github.com/login/oauth/access_token，获取token
   * @param accessTokenDTO
   * @return
   */
  public String getAccessToken(AccessTokenDTO accessTokenDTO){
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
    Request request = new Request.Builder()
            .url("https://github.com/login/oauth/access_token")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
      String string = response.body().string();
      System.out.println(string);
      return string;
    }catch (IOException e) {
        e.printStackTrace();
    }
    return null;
  }

  /**
   * https://api.github.com/user?access_token=0956163be1ef8bb29a3e82ca84cbd49a3fc69746
   * 获取到用户的一些信息
   * @param accessToken
   * @return
   */
  public GithubUser gitUser(String accessToken){
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://api.github.com/user?access_token="+accessToken)
            .build();
    try {
      Response response = client.newCall(request).execute();
      String string = response.body().string();
      GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
      return githubUser;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
