package practice.parksuehyuncrudapi.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.parksuehyuncrudapi.comment.entity.Comment;
import practice.parksuehyuncrudapi.comment.repository.CommentRepository;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.member.repository.MemberRepository;
import practice.parksuehyuncrudapi.post.entity.Post;
import practice.parksuehyuncrudapi.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public void addComment(String title, Long userId, String content) {
        Post post = postRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Comment comment = Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .build();
        commentRepository.save(comment);}
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long id) {
        return commentRepository.findByPostId(id).stream().collect(Collectors.toList());}

    public void updateComment(Long commentId, String content) {
        Comment comment = findByID(commentId);
        comment.updateContent(content);
        commentRepository.save(comment);
    }
    public void deleteComment(Long commentId) {
        Comment comment = findByID(commentId);
        commentRepository.delete(comment);
    }
    private Comment findByID(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }
}
