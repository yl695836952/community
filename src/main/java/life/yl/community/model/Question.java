package life.yl.community.model;

import lombok.Data;

/**
 * @author yanglin
 * @Date 2019-12-02-20:13
 */
@Data
public class Question {
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
}
