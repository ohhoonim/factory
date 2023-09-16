package dev.ohhoonim.factory.domain.post.api;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.PostUsecase;
import dev.ohhoonim.factory.domain.post.infra.PostQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements PostUsecase {
    
    private final PostQuery postQuery;
    
    @Override
    public List<Post> postList() {
        return postQuery.postList();
    }
    
}
