package life.yl.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 * @author yanglin
 * @Date 2019-12-08-18:19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

  @ExceptionHandler(Exception.class)
  ModelAndView handle(HttpServletRequest request,Throwable ex){
    HttpStatus status = getStatus(request);
    return new ModelAndView("error");
  }

  private HttpStatus getStatus(HttpServletRequest request){
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if(statusCode == null){
      return  HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}
