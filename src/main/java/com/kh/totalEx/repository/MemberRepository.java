package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// JUnit 테스트에서
// 1. 전체 회원 조회
// 2. 이메일로 개별 회원 조회
// 3. 가입 여부 확인 (이메일로 가입 여부 확인)
// 4. 로그인 체크
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Optional : null 방지
    Optional<Member> findByEmail(String email); // 개별 회원 정보 조회

    List<Member> findAll ();
    List<Member> findByEmailAndName(String email, String Name);
    boolean existsByEmail(String email);




}
