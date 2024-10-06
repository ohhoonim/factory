package dev.ohhoonim.factory.infra.business.post;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.infra.PostCommandPort;
import dev.ohhoonim.factory.domain.post.infra.PostQueryPort;
import dev.ohhoonim.factory.infra.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAdaptor implements PostQueryPort, PostCommandPort {

    private final PostRepository postRepository;

    @Override
    public List<Post> postList() {
        return postRepository.findAll().stream().map(p -> Post.builder().build()).toList();
    }
}
