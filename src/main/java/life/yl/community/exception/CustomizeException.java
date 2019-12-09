package life.yl.community.exception;

/**
 * 自定义异常类
 * @author yanglin
 * @create 2019-12-09 9:40
 */
public class CustomizeException extends RuntimeException {
  private String message;


  public CustomizeException(ICustomizeErrorCode errorCode) {
    this.message = errorCode.getMessage();
  }


  public CustomizeException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
