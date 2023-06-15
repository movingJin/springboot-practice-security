package com.example.springbootpractice.member.controller;

import com.example.springbootpractice.member.dto.MemberDto;
import com.example.springbootpractice.member.entity.Member;
import com.example.springbootpractice.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createForm() {
        return "member/createMemberForm";
    }

    @PostMapping("/member/new")
    public  String create(MemberDto form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }
}