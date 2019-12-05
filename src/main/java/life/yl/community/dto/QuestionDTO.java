package life.yl.community.dto;

import life.yl.community.model.User;
import lombok.Data;

/**
 * 包含了用户模型
 * @author yanglin
 * @create 2019-12-03 10:13
 */
@Data
public class QuestionDTO {

  private Integer id;
  private String title;
  private String description;
  private String tag;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer creator;
  private Integer viewCount;
  private Integer commentCount;
  private Integer likeCount;
  private User user;
}
