package io.starter.service;

import io.starter.domain.Member;
import io.starter.repository.MemberRepository;
import io.starter.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberRepository memberRepository; // 문제점 체크
    @Autowired
    MemberService memberService;

    @Test
    void 회원가입_성공() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long savedId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    void 회원가입_실패_중복_회원() {
        // given
        String sameName = "spring";
        Member member1 = new Member();
        member1.setName(sameName);

        Member member2 = new Member();
        member2.setName(sameName);

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}