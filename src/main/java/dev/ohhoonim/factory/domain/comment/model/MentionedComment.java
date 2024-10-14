package dev.ohhoonim.factory.domain.comment.model;

import java.util.List;

public record MentionedComment(
    String metionedAuthor,
    List<Comment> comments
) {
}
