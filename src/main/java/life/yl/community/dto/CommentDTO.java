package life.yl.community.dto;

import life.yl.community.model.User;
import lombok.Data;

/**
 * @author yanglin
 * @create 2019-12-11 15:51
 */
@Data
public class CommentDTO {

  private Long id;
  private Long parentId;
  private Integer type;
  private Long commentator;
  private Long gmtCreate;
  private Long gmtModified;
  private Long likeCount;
  private String content;
  private Integer commentCount;
  private User user;
}
