package com.example.shop.controller.Notice;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeContoller {

    private final NoticeService noticeService;

    @GetMapping("notice")
    public String notice() {
        return "notice/notice_list";
    }

    @GetMapping("/notice_memo")
    public String n_memo(){
        return "notice/notice_memo";
    }

    @GetMapping("/notice_add")
    public String notice_add(){
        return "notice/notice_insert";
    }

    @PostMapping("/notice_ok")
    public String notice_ok(@ModelAttribute NoticeDTO noticeDTO){
        System.out.println(noticeDTO);
        noticeService.save(noticeDTO);
        return "Member/login";
    }

}
