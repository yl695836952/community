package life.yl.community.enums;

/**
 * @author yanglin
 * @create 2019-12-18 17:56
 */
public enum NotificationStatusEnum {
  UNREAD(0),READ(1)
  ;
  private int status;

  NotificationStatusEnum(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
