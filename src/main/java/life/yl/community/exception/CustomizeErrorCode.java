package life.yl.community.exception;

/**
 * @author yanglin
 * @create 2019-12-09 9:57
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

  QUESTION_NOT_FOUND("你找的问题不存在，要不要换个试试！");

  @Override
  public String getMessage() {
    return message;
  }

  private String message;

   CustomizeErrorCode(String message) {
    this.message = message;
  }
}
