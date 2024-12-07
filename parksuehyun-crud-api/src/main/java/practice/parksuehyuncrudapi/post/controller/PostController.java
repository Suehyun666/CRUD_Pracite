package practice.parksuehyuncrudapi.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.member.service.MemberService;
import practice.parksuehyuncrudapi.post.controller.dto.request.CreatePostRequest;
import practice.parksuehyuncrudapi.post.controller.dto.response.PostResponse;
import practice.parksuehyuncrudapi.post.controller.dto.request.UpdatePostRequest;
import practice.parksuehyuncrudapi.post.entity.Post;
import practice.parksuehyuncrudapi.post.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;
    @PostMapping
    public void save(@RequestBody CreatePostRequest createPostRequest) {
        Member member = memberService.findMemberById(createPostRequest.getMemberId());
        Post post = createPostRequest.toEntity(member);
        postService.save(post);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{title}")
    public PostResponse getPost(@PathVariable String title) {
        return postService.getPost(title);
    }

    @PutMapping("/{title}")
    public void update(@PathVariable String title, @RequestBody UpdatePostRequest updatePostRequest) {
        postService.update(title, updatePostRequest);
    }

    @DeleteMapping("/{title}")
    public void delete(@PathVariable String title) {
        postService.delete(title);
    }
}
