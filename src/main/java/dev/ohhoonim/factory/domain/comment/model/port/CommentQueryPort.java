package dev.ohhoonim.factory.domain.comment.model.port;

import java.util.List;

import dev.ohhoonim.factory.domain.comment.model.Comment;

public interface CommentQueryPort {
    public List<Comment> findByBlockUuid(String blockUuid);
}
