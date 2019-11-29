package life.yl.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yanglin
 * @Date 2019-11-28-22:29
 */
@Controller
public class IndexController {

  @GetMapping("/")
  public String index(){
    return "index";
  }


}
