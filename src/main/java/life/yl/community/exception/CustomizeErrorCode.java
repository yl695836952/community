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
  COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不要换个试试！");

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
