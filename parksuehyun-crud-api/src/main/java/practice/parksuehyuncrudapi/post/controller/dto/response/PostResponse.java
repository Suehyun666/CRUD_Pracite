package practice.parksuehyuncrudapi.post.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.post.entity.Post;

@Getter
@AllArgsConstructor
public class PostResponse {
    //private String id;
    private String title;
    private String content;
    private String membername;
    //private Long memberid;

    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                //post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getName()
        );
    }
    public PostResponse (String title, String content, Member member){
        //this.id=id;
        this.title=title;
        this.content=content;
        this.membername=member.getName();
        //this.memberid=memberid;
    }
}
