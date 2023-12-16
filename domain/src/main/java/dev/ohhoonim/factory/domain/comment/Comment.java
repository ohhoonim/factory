package dev.ohhoonim.factory.domain.comment;

import java.time.LocalDateTime;

public class Comment {
    Integer id;
    String blockUuid;
    String author;
    String title;
    String contents;
    LocalDateTime created;
    LocalDateTime modified;

    public Comment(Integer id, String blockUuid, String author, String title, String contents, LocalDateTime created,
            LocalDateTime modified) {
        this.id = id;
        this.blockUuid = blockUuid;
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getBlockUuid() {
        return blockUuid;
    }

    public void setBlockUuid(String blockUuid) {
        this.blockUuid = blockUuid;
    }

    
}
