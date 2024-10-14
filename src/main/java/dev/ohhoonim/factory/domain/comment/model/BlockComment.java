package dev.ohhoonim.factory.domain.comment.model;

import java.util.List;

public record BlockComment(
    String blockUuid,
    List<Comment> comments
) {
    
}
