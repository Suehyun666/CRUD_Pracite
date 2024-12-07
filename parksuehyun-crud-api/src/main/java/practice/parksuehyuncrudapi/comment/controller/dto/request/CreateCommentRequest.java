package practice.parksuehyuncrudapi.comment.controller.dto.request;

import lombok.Getter;
import practice.parksuehyuncrudapi.comment.entity.Comment;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.post.entity.Post;
@Getter
public class CreateCommentRequest {
    private Member member;
    private Post post;

    private String content;
    private Long userId;

    public Comment toEntity(Comment comment) {
        return  Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .build();
    }
}
