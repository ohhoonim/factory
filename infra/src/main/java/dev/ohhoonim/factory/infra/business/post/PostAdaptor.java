package dev.ohhoonim.factory.infra.business.post;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.infra.PostCommand;
import dev.ohhoonim.factory.domain.post.infra.PostQuery;
import dev.ohhoonim.factory.infra.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAdaptor implements PostQuery, PostCommand {

    private final PostRepository postRepository;

    @Override
    public List<Post> postList() {
        return postRepository.findAll().stream().map(p -> Post.builder().build()).toList();
    }
}
