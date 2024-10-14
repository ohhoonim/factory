package dev.ohhoonim.factory.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.businessRepository.PostRepository;
import dev.ohhoonim.factory.domain.post.model.Post;
import dev.ohhoonim.factory.domain.post.model.port.PostCommandPort;
import dev.ohhoonim.factory.domain.post.model.port.PostQueryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostFactory implements PostQueryPort, PostCommandPort {

    private final PostRepository postRepository;

    @Override
    public List<Post> postList() {
        return postRepository.findAll().stream().map(p -> Post.builder().build()).toList();
    }
}
