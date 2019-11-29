package life.yl.community.dto;

import lombok.Data;

/**
 * 授权Token的对象类
 * @author yanglin
 * @create 2019-11-29 17:16
 */
@Data
public class AccessTokenDTO {
  private String client_id;
  private String client_secret;
  private String code;
  private String redirect_uri;
  private String state;

}
