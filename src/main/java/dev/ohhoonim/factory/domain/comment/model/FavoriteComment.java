package dev.ohhoonim.factory.domain.comment.model;

import java.util.List;

public record FavoriteComment(
    String markingAuthor,
    List<Comment> comments
) {
    
}
