package life.yl.community.controller;

import life.yl.community.dto.CommentDTO;
import life.yl.community.dto.ResultDTO;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.mapper.CommentMapper;
import life.yl.community.model.Comment;
import life.yl.community.model.User;
import life.yl.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 评论
 * @author yanglin
 * @create 2019-12-09 15:40
 */
@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/comment",method = RequestMethod.POST)
  public Object post(@RequestBody CommentDTO commentDTO,
                     HttpServletRequest request){
    User user = (User) request.getSession().getAttribute("user");
    if(user == null){
      return ResultDTO.errorOf(CustomizeErrorCode.No_LOGIN);
    }

    Comment comment = new Comment();
    comment.setParentId(commentDTO.getParentId());
    comment.setContent(commentDTO.getContent());
    comment.setType(commentDTO.getType());
    comment.setGmtCreate(System.currentTimeMillis());
    comment.setGmtModified(System.currentTimeMillis());
    comment.setCommentator(user.getId());
    comment.setLikeCount(0L);


    commentService.insert(comment);
    return ResultDTO.okOf();
  }
}
