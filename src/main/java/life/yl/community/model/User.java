package life.yl.community.model;

import lombok.Data;

/**
 * 用户
 * @author yanglin
 * @create 2019-12-02 14:09
 */
@Data
public class User {
  private Integer id;
  private String name;
  private String accountId;
  private String token;
  private Long gmtCreate;
  private Long gmtModified;
  private String avatarUrl;
}
