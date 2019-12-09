package life.yl.community.dto;

import lombok.Data;

/**
 * json---comment
 * @author yanglin
 * @create 2019-12-09 15:53
 */
@Data
public class CommentDTO {
  private Long parentId;
  private String content;
  private Integer type;
}
