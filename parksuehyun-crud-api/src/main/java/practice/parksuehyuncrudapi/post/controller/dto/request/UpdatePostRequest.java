package practice.parksuehyuncrudapi.post.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostRequest {
    private String title;
    private String content;
}
