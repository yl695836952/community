package life.yl.community.service;

import life.yl.community.dto.PaginationDTO;
import life.yl.community.dto.QuestionDTO;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import life.yl.community.mapper.QuestionExtMapper;
import life.yl.community.mapper.QuestionMapper;
import life.yl.community.mapper.UserMapper;
import life.yl.community.model.Question;
import life.yl.community.model.QuestionExample;
import life.yl.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

  @Autowired
  private QuestionExtMapper questionExtMapper;

  public PaginationDTO list(Integer page, Integer size) {
    Integer totalPage;

    PaginationDTO paginationDTO = new PaginationDTO();
    //分页赋值
    Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());//所有的数量

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
    QuestionExample questionExample = new QuestionExample();
    questionExample.setOrderByClause("gmt_create desc");
    List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(offset, size));
    List<QuestionDTO> questionDTOList = new ArrayList<>();


    for (Question question : list) {
      User user = userMapper.selectByPrimaryKey(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }
    paginationDTO.setQuestions(questionDTOList);



    return paginationDTO;
  }

  public PaginationDTO list(Long userId, Integer page, Integer size) {
    Integer totalPage;

    PaginationDTO paginationDTO = new PaginationDTO();
    //分页赋值
    QuestionExample example = new QuestionExample();
    example.createCriteria()
            .andCreatorEqualTo(userId);
    Integer totalCount = (int)questionMapper.countByExample(example);

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

    QuestionExample example1 = new QuestionExample();
    example1.createCriteria()
            .andCreatorEqualTo(userId);
    List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(example1, new RowBounds(offset, size));

    List<QuestionDTO> questionDTOList = new ArrayList<>();


    for (Question question : list) {
      User user = userMapper.selectByPrimaryKey(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }
    paginationDTO.setQuestions(questionDTOList);



    return paginationDTO;
  }

  public QuestionDTO getById(Long id) {
    Question question = questionMapper.selectByPrimaryKey(id);
    if(question == null){
      throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
    }
    User user = userMapper.selectByPrimaryKey(question.getCreator());
    QuestionDTO questionDTO = new QuestionDTO();
    BeanUtils.copyProperties(question, questionDTO);
    questionDTO.setUser(user);
    return questionDTO;
  }

  public void createOrUpdate(Question question) {
    if(question.getId() == null){
      //创建
      question.setGmtCreate(System.currentTimeMillis());
      question.setGmtModified(question.getGmtCreate());
      question.setViewCount(0);
      question.setLikeCount(0);
      question.setCommentCount(0);
      questionMapper.insert(question);
    }else {
      //更新
      Question updateQuestion = new Question();
      updateQuestion.setGmtModified(System.currentTimeMillis());
      updateQuestion.setTitle(question.getTitle());
      updateQuestion.setDescription(question.getDescription());
      updateQuestion.setTag(question.getTag());
      QuestionExample example = new QuestionExample();
      example.createCriteria()
              .andCreatorEqualTo(question.getId());
      int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
      if(updated != 1){
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
    }
  }

  /**
   * 增加阅读数
   * @param id
   */
  public void incView(Long id) {

    Question question = new Question();
    question.setId(id);
    question.setViewCount(1);
    questionExtMapper.incView(question);
  }

  public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
    if(StringUtils.isBlank(queryDTO.getTag())){
      return new ArrayList<>();
    }
    String[] tags = StringUtils.split(queryDTO.getTag(),",");
    String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
    Question question = new Question();
    question.setId(queryDTO.getId());
    question.setTag(regexpTag);

    List<Question> questions = questionExtMapper.selectRelated(question);
    List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(q, questionDTO);
      return questionDTO;
    }).collect(Collectors.toList());
    return questionDTOS;
  }
}
