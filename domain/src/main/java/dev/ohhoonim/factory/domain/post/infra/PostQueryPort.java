package dev.ohhoonim.factory.domain.post.infra;

import java.util.List;

import dev.ohhoonim.factory.domain.post.Post;

public interface PostQueryPort {

    List<Post> postList();
    
}
