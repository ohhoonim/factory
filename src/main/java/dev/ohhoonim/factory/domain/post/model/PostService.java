package dev.ohhoonim.factory.domain.post.model;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ohhoonim.factory.domain.post.model.port.PostQueryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService  {
    
    private final PostQueryPort postQuery;
    
    public List<Post> postList() {
        return postQuery.postList();
    }
    
}
