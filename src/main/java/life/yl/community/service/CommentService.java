package life.yl.community.service;

import life.yl.community.dto.CommentDTO;
import life.yl.community.enums.CommentTypeEnum;
import life.yl.community.enums.NotificationStatusEnum;
import life.yl.community.enums.NotificationTypeEnum;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import life.yl.community.mapper.*;
import life.yl.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private CommentExtMapper commentExtMapper;

  @Autowired
  private NotificationMapper notificationMapper;

  @Transactional
  public void insert(Comment comment) {
    if(comment.getParentId() == null || comment.getParentId() == 0){
      throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
    }

    if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
      throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
    }

    if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
      //回复评论
      Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
      if(dbComment == null){
        throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
      }
      commentMapper.insert(comment);

      //增加评论数
      Comment comment1 = new Comment();
      comment1.setId(comment.getParentId());
      comment1.setCommentCount(1);
      commentExtMapper.incCommentCount(comment1);
      //创建通知
      createNotify(comment, dbComment.getCommentator(), NotificationTypeEnum.REPLY_COMMENT);

    }else {
      //回复问题
      Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
      if(question == null){
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
      commentMapper.insert(comment);
      question.setCommentCount(1);
      questionExtMapper.incCommentCount(question);

      //创建通知
     createNotify(comment, question.getCreator(), NotificationTypeEnum.REPLY_QUESTION);
    }
  }

  /**
   * 创建通知
   * @param notificationType
   * @param comment
   *
   */
  private void createNotify(Comment comment, Long receiver, NotificationTypeEnum notificationType) {
    //回复的看与未看
    Notification notification = new Notification();
    notification.setGmtCreate(System.currentTimeMillis());
    notification.setType(notificationType.getType());
    notification.setOuterId(comment.getParentId());
    notification.setNotifier(comment.getCommentator());
    notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
    notification.setReceiver(receiver);
    notificationMapper.insert(notification);
  }

  public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
    CommentExample example = new CommentExample();
    example.createCriteria()
            .andParentIdEqualTo(id)
            .andTypeEqualTo(type.getType());
    example.setOrderByClause("gmt_create desc");
    List<Comment> comments = commentMapper.selectByExample(example);

    if(comments.size() ==0){
      return new ArrayList<>();
    }
    //获取去重的评论人
    Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());

    List<Long> userIds = new ArrayList();
    userIds.addAll(commentators);

    //获取评论人并转换为map
    UserExample userExample = new UserExample();
    userExample.createCriteria()
            .andIdIn(userIds);
    List<User> users = userMapper.selectByExample(userExample);
    Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

    //转换comment为commentDTO
    List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
      CommentDTO commentDTO = new CommentDTO();
      BeanUtils.copyProperties(comment, commentDTO);
      commentDTO.setUser(userMap.get(comment.getCommentator()));
      return commentDTO;
    }).collect(Collectors.toList());

    return commentDTOs;
  }
}
