package life.yl.community.controller;

import life.yl.community.dto.CommentCreateDTO;
import life.yl.community.dto.ResultDTO;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.model.Comment;
import life.yl.community.model.User;
import life.yl.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论
 * @author yanglin
 * @create 2019-12-09 15:40
 */
@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @ResponseBody
  @RequestMapping(value = "/comment",method = RequestMethod.POST)
  public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                     HttpServletRequest request){
    User user = (User) request.getSession().getAttribute("user");
    if(user == null){
      return ResultDTO.errorOf(CustomizeErrorCode.No_LOGIN);
    }

    Comment comment = new Comment();
    comment.setParentId(commentCreateDTO.getParentId());
    comment.setContent(commentCreateDTO.getContent());
    comment.setType(commentCreateDTO.getType());
    comment.setGmtCreate(System.currentTimeMillis());
    comment.setGmtModified(System.currentTimeMillis());
    comment.setCommentator(user.getId());
    comment.setLikeCount(0L);


    commentService.insert(comment);
    return ResultDTO.okOf();
  }
}
