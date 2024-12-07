package practice.parksuehyuncrudapi.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.parksuehyuncrudapi.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    void deleteByName(String name);
}
