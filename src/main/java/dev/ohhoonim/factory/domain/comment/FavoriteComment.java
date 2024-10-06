package dev.ohhoonim.factory.domain.comment;

import java.util.List;

public record FavoriteComment(
    String markingAuthor,
    List<Comment> comments
) {
    
}
