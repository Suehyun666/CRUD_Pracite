package practice.parksuehyuncrudapi.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import practice.parksuehyuncrudapi.comment.controller.dto.request.CreateCommentRequest;
import practice.parksuehyuncrudapi.comment.controller.dto.response.CommentResponse;
import practice.parksuehyuncrudapi.comment.service.CommentService;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.member.service.MemberService;
import practice.parksuehyuncrudapi.post.entity.Post;
import practice.parksuehyuncrudapi.post.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{title}/comments")
public class CommentController {
    private final CommentService commentService;
    private final MemberService memberService;
    private final PostService postService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String title, @RequestBody CreateCommentRequest createCommentRequest) {
        Member member = memberService.findMemberById(createCommentRequest.getUserId());
        commentService.addComment(title, member.getId(), createCommentRequest.getContent());}
    @GetMapping
    public List<CommentResponse> getComments(@PathVariable String title) {
        Post post = postService.findPostByTitle(title);
        return commentService.getCommentsByPostId(post.getId()).stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getContent(), comment.getMember().getId(), comment.getPost().getId()))
                .collect(Collectors.toList());
    }
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody String content) {
        commentService.updateComment(commentId, content);
    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
