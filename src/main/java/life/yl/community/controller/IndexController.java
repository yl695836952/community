package life.yl.community.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import life.yl.community.dto.QuestionDTO;
import life.yl.community.mapper.QuestionMapper;
import life.yl.community.mapper.UserMapper;
import life.yl.community.model.Question;
import life.yl.community.model.User;
import life.yl.community.service.QuestionService;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yanglin
 * @Date 2019-11-28-22:29
 */
@Controller
public class IndexController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private QuestionService questionService;


  @GetMapping("/")
  public String index(HttpServletRequest request,
                      Model model){
    Cookie[] cookies = request.getCookies();
    if(cookies != null && cookies.length !=0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          String token = cookie.getValue();
          User user = userMapper.findByToken(token);
          if (user != null) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }

    List<QuestionDTO> questionList = questionService.list();
    model.addAttribute("questions",questionList);
    return "index";
  }


}
