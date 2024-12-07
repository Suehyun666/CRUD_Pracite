package practice.parksuehyuncrudapi.post.controller.dto.request;

import lombok.Getter;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.post.entity.Post;

@Getter
public class CreatePostRequest {
    private String title;
    private String content;
    private Long memberId;
    public Post toEntity(Member member) {
        return  Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
