package life.yl.community.mapper;

import life.yl.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}