package life.yl.community.controller;

import life.yl.community.dto.QuestionDTO;
import life.yl.community.mapper.QuestionMapper;
import life.yl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yanglin
 * @create 2019-12-05 11:04
 */
@Controller
public class QuestionController {

  @Autowired
  private QuestionService questionService;

  @GetMapping("/question/{id}")
  public String question(@PathVariable(name = "id")Integer id,
                         Model model){
    QuestionDTO questionDTO = questionService.getById(id);
    model.addAttribute("question",questionDTO);
    return "question";
  }
}
