package dev.ohhoonim.factory.domain.comment;

import java.util.List;

public record MentionedComment(
    String metionedAuthor,
    List<Comment> comments
) {
}
