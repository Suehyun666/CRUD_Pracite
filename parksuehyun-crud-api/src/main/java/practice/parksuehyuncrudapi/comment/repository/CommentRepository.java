package practice.parksuehyuncrudapi.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.parksuehyuncrudapi.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // 특정 게시글에 대한 댓글 조회
    Optional<Comment> findById(Long id);
}