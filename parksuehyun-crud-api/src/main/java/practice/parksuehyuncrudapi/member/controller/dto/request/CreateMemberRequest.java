package practice.parksuehyuncrudapi.member.controller.dto.request;

import lombok.Getter;
import practice.parksuehyuncrudapi.member.entity.Member;

@Getter

public class CreateMemberRequest {
    private String username;
    private String email;
    private String password;

    public Member toEntity(){
        return  Member.builder()
                .name(username)
                .email(email)
                .password(password)
                .build();
    }
}
