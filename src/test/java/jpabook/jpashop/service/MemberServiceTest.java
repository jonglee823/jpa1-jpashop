package jpabook.jpashop.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("LEE");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("LEE");

        memberService.join(member);

        Member member1 = new Member();
        member1.setName("LEE");



        //then
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member1));
    }
}