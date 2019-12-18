package life.yl.community.controller;

import life.yl.community.dto.PaginationDTO;
import life.yl.community.model.User;
import life.yl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanglin
 * @Date 2019-12-04-21:59
 */
@Controller
public class ProfileController {

  @Autowired
  private QuestionService questionService;

  //@Autowired
  //private NotificationService notificationService;

  @GetMapping("/profile/{action}")
  public String profile(@PathVariable(name = "action")String action,
                        Model model,
                        HttpServletRequest request,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){


    User user =(User) request.getSession().getAttribute("user");

    if(user == null){
      return "redirect:/";
    }

    if("questions".equals(action)){
      model.addAttribute("section","questions");
      model.addAttribute("sectionName","我的提问");
      PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
      model.addAttribute("pagination",paginationDTO);
    }else if("replies".equals(action)){
      model.addAttribute("section","replies");
      model.addAttribute("sectionName","最新回复");
    }

    return "profile";
  }
}
