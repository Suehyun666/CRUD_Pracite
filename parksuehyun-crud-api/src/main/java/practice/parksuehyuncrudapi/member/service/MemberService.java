package practice.parksuehyuncrudapi.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.parksuehyuncrudapi.member.controller.dto.request.CreateMemberRequest;
import practice.parksuehyuncrudapi.member.controller.dto.reponse.MemberResponse;
import practice.parksuehyuncrudapi.member.controller.dto.request.UpdateMemberRequest;
import practice.parksuehyuncrudapi.member.entity.Member;
import practice.parksuehyuncrudapi.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Component
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void signup(CreateMemberRequest creatememberRequest) {
        Member member = creatememberRequest.toEntity();
        memberRepository.save(member);
    }
    @Transactional(readOnly = true)
    public MemberResponse getMember(final Long id) {
        Member member = findMemberById(id);
        return new MemberResponse(member.getName(), member.getEmail());
    }
    public void updateMember(final Long id, UpdateMemberRequest updateRequest) {
        Member member = findMemberById(id);
        member.updateProfile(updateRequest.getName(), updateRequest.getEmail());
    }

    public void deleteMember(final Long id) {
        Member member = findMemberById(id);
        memberRepository.delete(member);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
