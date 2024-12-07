package practice.parksuehyuncrudapi.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.member.repository.MemberRepository;
import practice.parksuehyuncrudapi.post.controller.dto.request.UpdatePostRequest;
import practice.parksuehyuncrudapi.post.controller.dto.response.PostResponse;
import practice.parksuehyuncrudapi.post.entity.Post;
import practice.parksuehyuncrudapi.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void save(Post post) {
        Member member = memberRepository.findById(post.getMember().getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        postRepository.save(post);
    }
    @Transactional(readOnly = true)
    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public PostResponse getPost(String title) {
        Post post = findPostByTitle(title);
        return new PostResponse(post.getTitle(), post.getContent(), post.getMember());
    }

    public void update(String title, UpdatePostRequest updatePostRequest) {
        Post post = findPostByTitle(title);
        post.update(updatePostRequest.getTitle(), updatePostRequest.getContent());
    }

    public void delete(String title) {
        Post post = findPostByTitle(title);
        postRepository.delete(post);
    }
    public Post findPostByTitle(String title) {
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }
}