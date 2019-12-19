package life.yl.community.dto;

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
  private Long notifier;
  private String notifierName;
  private String outerTitle;
  private String typeName;
  private Long outerId;

  private Integer type;
  //P49 ---51
}
