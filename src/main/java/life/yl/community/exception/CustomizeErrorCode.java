package life.yl.community.exception;

/**
 * @author yanglin
 * @create 2019-12-09 9:57
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

  QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换个试试！"),
  TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
  No_LOGIN(2003, "当前操作需要登陆，请先登陆后重试！"),
  SYS_ERROR(2004,"服务器爆炸，请稍后重试~~"),
  TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！"),
  COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不要换个试试！"),
  COMMENT_IS_EMPTY(2007,"输入的内容不能够为空！"),
  READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的信息呢？"),
  NOTIFICATION_NOT_FOUNT(2009,"消息莫非是不翼而飞了？");

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Integer getCode() {
    return code;
  }

  private Integer code;
  private String message;

   CustomizeErrorCode(Integer code, String message) {
    this.message = message;
    this.code = code;
  }
}
