package dev.ohhoonim.factory.domain.comment.infra;

import java.util.List;

import dev.ohhoonim.factory.domain.comment.Comment;

public interface CommentQueryPort {
    public List<Comment> findByBlockUuid(String blockUuid);
}
