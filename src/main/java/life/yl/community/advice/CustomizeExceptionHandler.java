package life.yl.community.advice;

import life.yl.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
  ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){

    if(ex instanceof CustomizeException){
      model.addAttribute("message", ex.getMessage());
    } else {
      model.addAttribute("message","服务器爆炸，请稍后重试~~");
    }
    return new ModelAndView("error");
  }
}
