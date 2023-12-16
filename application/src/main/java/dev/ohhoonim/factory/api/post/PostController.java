package dev.ohhoonim.factory.api.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.factory.domain.post.Post;
import dev.ohhoonim.factory.domain.post.api.PostAgent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostAgent postService;

    @Operation(summary = "post 목록 조회", description = "Post 목로을 조회한다.", security = {
            @SecurityRequirement(name = "bearer-key") }, tags = { "Post" })
    @GetMapping("/list")
    public List<Post> postList() {
        return postService.postList();
    }

    @Operation(summary = "에러 발생 테스트용", description = "에러발생 테스트용", security = {
            @SecurityRequirement(name = "bearer-key") }, tags = { "Post" })
    @GetMapping("/errorList")
    public void errorList() throws Exception {
        throw new Exception("임의로 exception 발생");
    }

}
