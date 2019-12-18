package life.yl.community.dto;

import life.yl.community.model.User;
import lombok.Data;

/**
 * @author yanglin
 * @create 2019-12-18 18:18
 */
@Data
public class NotificationDTO {

  private Long id;
  private Long gmtCreate;
  private Integer status;
  private User user;
  private String outerTile;
}
