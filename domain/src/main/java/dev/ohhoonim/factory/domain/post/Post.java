package dev.ohhoonim.factory.domain.post;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Post {
    private Long postId;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdDateTime;

    @Builder
    public Post(Long postId, String title, String contents, String author, LocalDateTime createdDateTime) {
        this.postId = postId;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.createdDateTime = createdDateTime;
    }
}
