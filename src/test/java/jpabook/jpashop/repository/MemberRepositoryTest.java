package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "local")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional
    @DisplayName("insert Member Test")
    //@Rollback(false)
    public void testMember() {
        Member member = Member.builder()
                            .name("memberA")
                            .build();

        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.find(savedId);

        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getName(), member.getName());
        assertEquals(findMember, member);

    }
}