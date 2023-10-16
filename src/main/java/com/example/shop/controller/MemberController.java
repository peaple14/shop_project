package com.example.shop.controller;
import com.example.shop.dto.MemberDTO;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String joinForm() {
        return "Member/join";
    }

    @GetMapping("/member/login")
    public String login(){
        return "Member/login";
    }

    @PostMapping("/join_ok")
    public String join_ok(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "Member/login";
    }
}
