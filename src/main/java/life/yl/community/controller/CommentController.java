package life.yl.community.controller;

import life.yl.community.dto.CommentCreateDTO;
import life.yl.community.dto.CommentDTO;
import life.yl.community.dto.ResultDTO;
import life.yl.community.enums.CommentTypeEnum;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.model.Comment;
import life.yl.community.model.User;
import life.yl.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
      return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
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

  /**
   * 二级评论
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
  public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
    List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
    return ResultDTO.okOf(commentDTOS);
  }
}
