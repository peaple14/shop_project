package com.example.shop.controller;

import com.example.shop.dto.AdminDTO;
import com.example.shop.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String index(){
        return "admin";
    }
    @GetMapping("admin/notice")
    public String notice(){
        return "/admin/notice_write.html";
    }

    @PostMapping("/admin/login")
    public String ok(@ModelAttribute AdminDTO adminDTO) {
        AdminDTO loginResult = adminService.login(adminDTO);
        if(loginResult != null){
            //로그인 성공
            return "/admin/goods";
        }
        else{
            //로그인 실패
            return "/admin";
        }
    }

}
