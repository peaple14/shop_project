package com.example.shop.controller.login;

import com.example.shop.dto.MemberDTO;
import com.example.shop.dto.loginDTO.LoginDTO;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class LoginController {
    //로그인오류이유:회원가입시 xx,xx같은 형식으로 2번 db에 저장됨.
    //로그인,회원가입용


    private final MemberService memberService;

    //가입폼으로
    @GetMapping("/member/join")
    public String joinForm() {
        return "Member/join";
    }

    //로그인폼으로
    @GetMapping("/member/login")
    public String login(@ModelAttribute("LoginForm") LoginDTO form){
        return "Member/login";
    }

    //가입시도
    @PostMapping("/join_ok")
    public String join_ok(@ModelAttribute MemberDTO memberDTO,HttpSession session){
        //비어있거나 ""이 들어갈시.->복잡한 처리는 서비스로 분리.
        if (!memberService.isMemberDTOValid(memberDTO)) {
            return "redirect:/Member/login";
        }
        memberService.save(memberDTO);
        session.setAttribute("loginId", memberDTO.getUserId());//회원가입되었다고 세션포함해서 메인화면으로
        session.setAttribute("loginName", memberDTO.getUserName());

        return "/index";
    }

    //로그인시도
    @PostMapping("/login_ok")
    public String login_ok(@ModelAttribute MemberDTO memberDTO, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getUserId());
            session.setAttribute("loginName", loginResult.getUserName());
            System.out.println("로그인 성공");
            return "redirect:/"; // 로그인 성공 시 리다이렉트할 URL을 설정하세요.
        } else {
            System.out.println("로그인 실패");
            return "redirect:/login";
        }
    }


    //로그아웃기능
    @PostMapping ("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


    //id중복확인
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("userId") String userId) {
        System.out.println("userId = " + userId);
        String checkResult = memberService.idCheck(userId);
        return checkResult;

    }




}
