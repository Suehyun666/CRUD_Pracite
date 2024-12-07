package practice.parksuehyuncrudapi.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.parksuehyuncrudapi.member.entity.Member;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        setMember(member);
    }
    public static Post from(final String title, final String content,final Member member) {
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
    public void update(String title, String content) {
        if (title == null || title.isBlank()) {throw new IllegalArgumentException("Title cannot be null or blank");}
        if (content == null || content.isBlank()) {throw new IllegalArgumentException("Content cannot be null or blank");}
        this.title = title;
        this.content = content;
    }
    public void setMember(Member member) {
        if (this.member != null) {this.member.getPosts().remove(this); }
        this.member = member;
        if (member != null && !member.getPosts().contains(this)) {member.getPosts().add(this); }
    }
}
