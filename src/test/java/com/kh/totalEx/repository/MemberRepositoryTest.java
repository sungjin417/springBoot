package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    public void createMemberTest() {
        for (int i =0; i <=10; i++) {
            Member member = new Member();
            member.setName("테스트 유저"+i);
            member.setPwd("qwe"+i);
            member.setEmail("테스트 이메일" + i);
            member.setImage("테스트 이미지" + i);
            member.setRegDate(LocalDateTime.now());
            memberRepository.save(member);
        }
    }
    @Test
    @DisplayName("회원 전체 정보 조회 테스트")
    public void findMemberAllTest() {
        this.createMemberTest();
        List<Member> memberList = memberRepository.findAll();
        for (Member e : memberList) System.out.println(e.toString());
    }
    @Test
    @DisplayName("이메일로 회원 조회 테스트")
    public void findByEmailTest() {
        this.createMemberTest();
        Optional<Member> memberList = memberRepository.findByEmail("테스트 이메일1");
        System.out.println(memberList.toString());
    }
    @Test
    @DisplayName("가입 여부 확인 테스트")
    public void findByLoginTest() {
        this.createMemberTest();
        Optional<Member> memberList = memberRepository.findByEmail("테스트 이메일1");
        System.out.println(memberList.isPresent());
    }
    @Test
    @DisplayName("Null값 확인 테스트")
    public void fidByEmailTest() {
        Member member = new Member();
        member.setName("테스트 유저");
        member.setPwd("qwe");
        member.setEmail("null");
        member.setImage("테스트 이미지" );
        member.setRegDate(LocalDateTime.now());
        memberRepository.save(member);
        List<Member> memberList = memberRepository.findByEmailAndName("null", "테스트 유저");
        System.out.println(memberList.toString());
    }
}