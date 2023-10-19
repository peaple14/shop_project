package com.example.shop.controller;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminNoticeController {


    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model,
                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword) {
        Page<NoticeEntity> list = null;

        if(searchKeyword == null) {//검색어 없을때
            list = noticeService.noticelist(pageable);
        }else {//검색어 있을때
            list = noticeService.noticeSearchList(searchKeyword, pageable);//검색어와 함께
        }

        int nowPage = list.getPageable().getPageNumber() + 1;//현재페이지
        int startPage = Math.max(nowPage - 4, 1);//시작페이지
        int endPage = Math.min(nowPage + 5, list.getTotalPages());//마지막페이지

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/notice/notice_list";
    }

    @GetMapping("/notice_memo/{id}")
    public String n_memo(Model model, @PathVariable("id") long id){
        model.addAttribute("notice",noticeService.noticeView(id));
        return "admin/notice/notice_memo";
    }

    @GetMapping("/notice_add")
    public String notice_add(){
        return "admin/notice/notice_insert";
    }

    @PostMapping("/notice_ok")
    public String notice_ok(@ModelAttribute NoticeDTO noticeDTO) throws IOException {
        noticeService.save(noticeDTO);
        return "redirect:/admin/notice";
    }

    @GetMapping("/notice_edit/{id}")
    public String n_edit(Model model, @PathVariable("id") long id){
        model.addAttribute("notice",noticeService.noticeView(id));
        return "admin/notice/notice_edit";
    }

    @PostMapping("/edit_ok")
    public String edit_ok(@RequestParam(name = "id") Long id,@ModelAttribute NoticeDTO noticeDTO){
        noticeService.update(id,noticeDTO);
        return "redirect:/admin/notice";
    }

    @GetMapping("/notice/delete")
    public String noticeDelete(@RequestParam(name = "id") Long id, Model mode){
        noticeService.noticeDelete(id);
        return "redirect:/admin/notice";
    }

}
