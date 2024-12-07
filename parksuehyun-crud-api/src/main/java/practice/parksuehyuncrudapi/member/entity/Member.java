package practice.parksuehyuncrudapi.member.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.parksuehyuncrudapi.post.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor  //기본 생성자를 위해서+필드있으면 안됨?
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "member",cascade=CascadeType.ALL)//orphanRemoval=true
    private List<Post> posts = new ArrayList<>();

    @Builder
    private Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public void addPost(Post post) {
        this.posts.add(post);
        post.setMember(this); // 양방향 관계 설정
    }
    public void removePost(Post post) {
        this.posts.remove(post);
        post.setMember(null); // 연관 관계 해제
    }
}