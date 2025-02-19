package dev.ohhoonim.factory.domain.comment.model;

import java.time.LocalDateTime;

public record Comment(
    Integer id,
    String blockUuid,
    String author,
    String title,
    String contents,
    LocalDateTime created,
    LocalDateTime modified
) {
}
