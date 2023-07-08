package com.example.springbootpractice.member.controller;

import com.example.springbootpractice.member.dto.MemberDto;
import com.example.springbootpractice.member.entity.Member;
import com.example.springbootpractice.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login(Model model,
                        RedirectAttributes redirectAttributes) {
        Optional<Object> isRegister = Optional.ofNullable(model.asMap().get("isRegister"));
        if(isRegister.isPresent()){
            model.addAttribute("message", "회원가입에 성공했습니다.");
        }
        return "/member/signIn";
    }

    @GetMapping("/register")
    public String createForm() {
        return "member/createMemberForm";
    }

    @PostMapping("/member/new")
    public  String create(MemberDto form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }
        Member member = Member.builder()
                .email(form.getEmail())
                .password(bCryptPasswordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .role("ROLE_USER")
                .build();
        try {
            memberService.join(member);
        }catch (Exception e){
            return "redirect:/register";
        }
        redirectAttributes.addFlashAttribute("isRegister", true);
        return "redirect:/login";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }

    @GetMapping("/member/welcome")
    public String welcome(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            model.addAttribute("userName", userName);
        }

        return "member/welcome";
    }
}