package life.yl.community.exception;

/**
 * 自定义异常类
 * @author yanglin
 * @create 2019-12-09 9:40
 */
public class CustomizeException extends RuntimeException {
  private String message;
  private Integer code;

  public CustomizeException(ICustomizeErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }



  @Override
  public String getMessage() {
    return message;
  }

  public Integer getCode() {
    return code;
  }
}
