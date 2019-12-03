package life.yl.community.service;

import life.yl.community.dto.QuestionDTO;
import life.yl.community.mapper.QuestionMapper;
import life.yl.community.mapper.UserMapper;
import life.yl.community.model.Question;
import life.yl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglin
 * @create 2019-12-03 10:15
 */
@Service
public class QuestionService {

  @Autowired
  private QuestionMapper questionMapper;

  @Autowired
  private UserMapper userMapper;

  public List<QuestionDTO> list() {
    List<Question> list = questionMapper.list();
    List<QuestionDTO> questionDTOList = new ArrayList<>();
    for (Question question : list) {
      User user = userMapper.findById(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }
    return questionDTOList;
  }
}
