package dev.ohhoonim.factory.api.post;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.api.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public List<Post> postList() {
        return postService.postList();
    }

    @GetMapping("/errorList")
    public void errorList() throws Exception {
        throw new Exception("임의로 exception 발생");
    } 
    
}
