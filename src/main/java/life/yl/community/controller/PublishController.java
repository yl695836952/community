package life.yl.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yanglin
 * @create 2019-12-02 17:15
 */
@Controller
public class PublishController {

  @GetMapping("/publish")
  public String publish(){
    return "publish";
  }
}
