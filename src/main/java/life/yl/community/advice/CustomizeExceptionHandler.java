package life.yl.community.advice;

import com.alibaba.fastjson.JSON;
import life.yl.community.dto.ResultDTO;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 * @author yanglin
 * @Date 2019-12-08-18:19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

  @ExceptionHandler(Exception.class)
  ModelAndView handle(HttpServletRequest request, Throwable ex, Model model,
                HttpServletResponse response){

    String contentType = request.getContentType();
    if("application/json".equals(contentType)){
      ResultDTO resultDTO;
      //返回 json
      if(ex instanceof CustomizeException){
        resultDTO =  ResultDTO.errorOf((CustomizeException) ex);
      } else {
        resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
      }

      try {
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultDTO));
        writer.close();
      } catch (IOException e) {

      }
      return null;
    }else {
      //返回页面跳转
      if(ex instanceof CustomizeException){
        model.addAttribute("message", ex.getMessage());
      } else {
        model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
      }
      return new ModelAndView("error");
    }
  }
}
