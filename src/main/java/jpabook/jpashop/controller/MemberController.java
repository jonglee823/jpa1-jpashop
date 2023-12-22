package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.request.MemberRequest;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value ="/members/new")
    public String createFrom(Model model){
        model.addAttribute("memberRequest", new MemberRequest());
        return "members/createMemberForm";
    }

    @PostMapping(value ="/members/new")
    public String create(@Valid MemberRequest memberRequest, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member(memberRequest);
        memberService.join(member);
        return "redirect:/";
    }

    /**
     * 회원 목록 조회
     * @param model
     * @return
     */

    @GetMapping(value="/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
