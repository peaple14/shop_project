package com.example.shop.controller.admin;
import com.example.shop.entity.MemberEntity;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class MemberController {

    private final MemberService memberService;


    //멤버리스트
    @GetMapping("/member")
    public String notice(Model model,
                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , String searchKeyword) {
        Page<MemberEntity> list = null;

        if(searchKeyword == null) {//검색어 없을때
            list = memberService.memberlist(pageable);
        }else {//검색어 있을때
            list = memberService.noticeSearchList(searchKeyword, pageable);//검색어와 함께
        }

        int nowPage = list.getPageable().getPageNumber() + 1;//현재페이지
        int startPage = Math.max(nowPage - 4, 1);//시작페이지
        int endPage = Math.min(nowPage + 5, list.getTotalPages());//마지막페이지

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/member_list";
    }

    @GetMapping("/member/delete")
    public String noticeDelete(@RequestParam(name = "id") Long id, Model mode){
        memberService.memberDelete(id);
        return "redirect:/admin/member";
    }

}
