package dev.ohhoonim.factory.domain.comment.model.port;

import dev.ohhoonim.factory.domain.comment.model.Comment;

public interface CommentCommandPort {

    void registComment(Comment newComment);
    
}
