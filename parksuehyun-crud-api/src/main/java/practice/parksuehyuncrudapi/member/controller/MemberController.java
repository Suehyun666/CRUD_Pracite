package practice.parksuehyuncrudapi.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.parksuehyuncrudapi.member.controller.dto.reponse.MemberResponse;
import practice.parksuehyuncrudapi.member.controller.dto.request.CreateMemberRequest;
import practice.parksuehyuncrudapi.member.controller.dto.request.UpdateMemberRequest;
import practice.parksuehyuncrudapi.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void signup(@RequestBody CreateMemberRequest creatememberRequest) {
        memberService.signup(creatememberRequest);
    }
    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable("id") Long id){
        return memberService.getMember(id);
    }
    @PutMapping ("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody UpdateMemberRequest updatememberRequest){
        memberService.updateMember(id, updatememberRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        memberService.deleteMember(id);
    }

}
