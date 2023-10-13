package com.example.shop.controller.Notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeContoller {

    @GetMapping("notice")
    public String notice() {
        return "notice/notice_list";
    }

    @GetMapping("/notice_memo")
    public String n_memo(){
        return "notice/notice_memo";
    }
}
