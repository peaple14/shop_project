package com.example.shop.controller.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/member/join")
    public String joinForm() {
        return "Member/join";
    }

    @GetMapping("/member/login")
    public String login(){
        return "Member/login";
    }

    @PostMapping("/join_ok")
    public String join_ok(@RequestParam("user_id") String user_id,
                          @RequestParam("user_pass")String user_pass ,
                          @RequestParam("user_name") String user_name){
        System.out.println("user_id = " + user_id + ", user_pass = " + user_pass + ", user_name = " + user_name);
        return "Member/login";
    }
}
