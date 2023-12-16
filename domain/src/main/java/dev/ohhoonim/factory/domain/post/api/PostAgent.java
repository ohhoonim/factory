package dev.ohhoonim.factory.domain.post.api;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.PostUsecase;
import dev.ohhoonim.factory.domain.post.infra.PostQueryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostAgent implements PostUsecase {
    
    private final PostQueryPort postQuery;
    
    @Override
    public List<Post> postList() {
        return postQuery.postList();
    }
    
}
