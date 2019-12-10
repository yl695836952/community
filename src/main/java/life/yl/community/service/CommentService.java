package life.yl.community.service;

import life.yl.community.enums.CommentTypeEnum;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import life.yl.community.mapper.CommentMapper;
import life.yl.community.mapper.QuestionExtMapper;
import life.yl.community.mapper.QuestionMapper;
import life.yl.community.model.Comment;
import life.yl.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yanglin
 * @create 2019-12-09 16:21
 */
@Service
public class CommentService {


  @Autowired
  private QuestionMapper questionMapper;

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private QuestionExtMapper questionExtMapper;

  @Transactional
  public void insert(Comment comment) {
    if(comment.getParentId() == null || comment.getParentId() == 0){
      throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
    }

    if(comment.getType() == null || CommentTypeEnum.isExist(comment.getType())){
      throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
    }

    if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
      //回复评论
      Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
      if(dbComment == null){
        throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
      }
      commentMapper.insert(comment);
    }else {
      //回复问题
      Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
      if(question == null){
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
      commentMapper.insert(comment);
      question.setCommentCount(1);
      questionExtMapper.incCommentCount(question);
    }
  }
}
