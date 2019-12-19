package life.yl.community.controller;

import life.yl.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yanglin
 * @create 2019-12-19 18:07
 */
@Controller
public class FileController {

  @RequestMapping("/file/upload")
  @ResponseBody
  public FileDTO upload(){

    FileDTO fileDTO = new FileDTO();
    fileDTO.setSuccess(1);
    fileDTO.setUrl("/images/beautiful.jpg");
    return fileDTO;
  }
}
