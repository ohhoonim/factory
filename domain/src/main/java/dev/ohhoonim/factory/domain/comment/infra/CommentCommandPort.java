package dev.ohhoonim.factory.domain.comment.infra;

import dev.ohhoonim.factory.domain.comment.Comment;

public interface CommentCommandPort {

    void registComment(Comment newComment);
    
}
