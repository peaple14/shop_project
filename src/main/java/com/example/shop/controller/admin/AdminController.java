package com.example.shop.controller.admin;
import com.example.shop.dto.AdminDTO;
import com.example.shop.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    @GetMapping("")
    public String index(){
        return "/admin/admin";
    }

    @PostMapping("/login")
    public String save(@ModelAttribute AdminDTO adminDTO, HttpSession session, Model model){
        AdminDTO loginResult = adminService.login(adminDTO); //서비스에서 처리한결과 loginresult로.
        if(loginResult != null){
            //로그인 성공
            session.setAttribute("loginId",loginResult.getAdmId());
            session.setAttribute("loginName", loginResult.getAdmName());
            model.addAttribute("session", session);
            System.out.println("로그인 성공");
            return "redirect:/admin/notice";
        }
        else{
            System.out.println("로그인 실패");
            //로그인 실패
        }
        return null;
    }



    @PostMapping ("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin";  // templates 폴더의 index.html 을 찾아감
    }







}
