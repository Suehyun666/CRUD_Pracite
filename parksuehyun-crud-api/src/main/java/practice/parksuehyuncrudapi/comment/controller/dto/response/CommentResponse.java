package practice.parksuehyuncrudapi.comment.controller.dto.response;

public class CommentResponse {

    private Long id;
    private String content;
    private Long userId;
    private Long postId;

    public CommentResponse(Long id, String content, Long userId, Long postId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }
}