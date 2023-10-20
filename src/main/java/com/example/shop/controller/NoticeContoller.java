package com.example.shop.controller;

import com.example.shop.dto.CommentDTO;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.service.CommentService;
import com.example.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeContoller {


    //수정html만들기 , 검색쪽 에러 물어보기
    private final NoticeService noticeService;
    private final CommentService commentService;

    @GetMapping("/notice")
    public String notice(Model model,
                         @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword) {
        Page<NoticeEntity> list = null;

        if(searchKeyword == null) {//검색어 없을때
            list = noticeService.noticelist(pageable);
        }else {//검색어 있을때
            list = noticeService.noticeSearchList(searchKeyword, pageable);//검색어와 함께
        }

        int nowPage = list.getPageable().getPageNumber() + 1;  // 현재 페이지
        int startPage = Math.max(nowPage - 4, 1);  // 시작 페이지
        int endPage = Math.min(nowPage + 5, list.getTotalPages());  // 마지막 페이지
        if (list.isEmpty()) {//검색후 아무것도 없을때
            nowPage = 0;
            startPage = 1;
            endPage = 0;
        }
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "notice/notice_list";
    }

    @GetMapping("/notice_memo/{id}")
    public String n_memo(Model model, @PathVariable long id){
        List<CommentDTO> comments = commentService.getCommentsByNoticeId(id);
        model.addAttribute("comments", comments);
        System.out.println("댓글이거다:" + comments);
        model.addAttribute("notice",noticeService.findById(id));
        return "notice/notice_memo";
    }

//    //페이징나누기(다른방식)
//    @GetMapping("paging")
//    public String paging(@PageableDefault(page=1)Pageable pageable, Model model){
//        pageable.getPageNumber();
//        Page<NoticeDTO> noticeList = noticeService.paging(pageable);
//        int blockLimit = 3; //페이지 블록의 갯수
//        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit +1;
//        int endPage = ((startPage + blockLimit -1) < noticeList.getTotalPages()) ? startPage + blockLimit -1 : noticeList.getTotalPages();
//
//        model.addAttribute("noticeList", noticeList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "paging";
//    }













}
