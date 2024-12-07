package practice.parksuehyuncrudapi.member.controller.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private String username;
    private String email;
}
