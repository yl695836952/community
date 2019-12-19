package life.yl.community.controller;

import life.yl.community.dto.NotificationDTO;
import life.yl.community.enums.NotificationTypeEnum;
import life.yl.community.model.User;
import life.yl.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanglin
 * @create 2019-12-19 13:41
 */
@Controller
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @GetMapping("/notification/{id}")
  public String profile(@PathVariable(name = "id")Long id,
                        HttpServletRequest request){


    User user =(User) request.getSession().getAttribute("user");

    if(user == null){
      return "redirect:/";
    }

    NotificationDTO notificationDTO = notificationService.read(id,user);

    if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
      return "redirect:/question/"+notificationDTO.getOuterId();
    }else {
      return "redirect:/";
    }


  }
}
