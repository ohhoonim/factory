package dev.ohhoonim.factory.domain.post.model.port;

import java.util.List;

import dev.ohhoonim.factory.domain.post.model.Post;

public interface PostQueryPort {

    List<Post> postList();
    
}
