package io.starter.repository;

import io.starter.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void setUp() {
        repository.clearStore(); // 데이터를 깨끗하게 지워야한다.
    }

    @Test // 테스트 메소드 실행 순서 알 수 없다. 순서에 의존하면 안됨.
    void save() {
        List<Member> before = repository.findAll();
        assertThat(before).isEmpty();

        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        List<Member> after = repository.findAll();


        assertThat(after).isNotEmpty(); // AssertJ Core Api 활용
        assertThat(after.size()).isEqualTo(1);
        assertThat(after).contains(member);
    }

    @Test
    void saveAndFindById() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    void saveAndFindByName() {
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result2 = repository.findByName("spring2").get();
        assertThat(member2).isEqualTo(result2);
    }


    @Test
    void findAll() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> members = repository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }
}