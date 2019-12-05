package life.yl.community.service;

import life.yl.community.dto.PaginationDTO;
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

  public PaginationDTO list(Integer page, Integer size) {
    Integer totalPage;

    PaginationDTO paginationDTO = new PaginationDTO();
    //分页赋值
    Integer totalCount = questionMapper.count();//所有的数量

    //判断页数能不能整除
    if(totalCount % size == 0){
      totalPage = totalCount / size;
    }else {
      totalPage = totalCount / size + 1;
    }

    if(page<1){
      page = 1;
    }
    if(page>totalPage){
      page = totalPage;
    }

    paginationDTO.setPagination(totalPage,page);
    //分页 size*(page-1)
    Integer offset = size * (page - 1 );
    List<Question> list = questionMapper.list(offset,size);
    List<QuestionDTO> questionDTOList = new ArrayList<>();


    for (Question question : list) {
      User user = userMapper.findById(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }
    paginationDTO.setQuestions(questionDTOList);



    return paginationDTO;
  }

  public PaginationDTO list(Integer userId, Integer page, Integer size) {
    Integer totalPage;

    PaginationDTO paginationDTO = new PaginationDTO();
    //分页赋值
    Integer totalCount = questionMapper.countByUserId(userId);//所有的数量

    //判断页数能不能整除
    if(totalCount % size == 0){
      totalPage = totalCount / size;
    }else {
      totalPage = totalCount / size + 1;
    }

    if(page<1){
      page = 1;
    }
    if(page>totalPage){
      page = totalPage;
    }

    paginationDTO.setPagination(totalPage,page);

    //分页 size*(page-1)
    Integer offset = size * (page - 1 );
    List<Question> list = questionMapper.listByUserId(userId,offset,size);
    List<QuestionDTO> questionDTOList = new ArrayList<>();


    for (Question question : list) {
      User user = userMapper.findById(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }
    paginationDTO.setQuestions(questionDTOList);



    return paginationDTO;
  }

  public QuestionDTO getById(Integer id) {
    Question question = questionMapper.getById(id);
    User user = userMapper.findById(question.getCreator());
    QuestionDTO questionDTO = new QuestionDTO();
    BeanUtils.copyProperties(question, questionDTO);
    questionDTO.setUser(user);
    return questionDTO;
  }
}
