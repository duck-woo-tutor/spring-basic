package io.starter.repository;

import io.starter.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
     JPQL : select m from Member m where m.name = ?
     -> Spring Data Jpa가 reflection등을 사용해, 해당 엔티티의 필드명을 가지고와서 메소드명을 분석해, JPQL 쿼리를 생성한다.
     */
    @Override
    Optional<Member> findByName(String name);
}
