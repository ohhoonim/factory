package dev.ohhoonim.factory.domain.comment;

import java.util.List;

public record BlockComment(
    String blockUuid,
    List<Comment> comments
) {
    
}
