package com.example.shop.controller.admin;

import com.example.shop.dto.GoodsDTO;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.goods.GoodsEntity;
import com.example.shop.entity.notice.NoticeEntity;
import com.example.shop.service.GoodsService;
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
public class AdminGoodsController {


    private final GoodsService goodsService;

    //공지리스트
    @GetMapping("/goods")
    public String notice(Model model,
                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword) {
        Page<GoodsEntity> list = null;

        if(searchKeyword == null) {//검색어 없을때
            list = goodsService.goodsList(pageable);
        }else {//검색어 있을때
            list = goodsService.goodsSearchList(searchKeyword, pageable);//검색어와 함께
        }

        int nowPage = list.getPageable().getPageNumber() + 1;//현재페이지
        int startPage = Math.max(nowPage - 4, 1);//시작페이지
        int endPage = Math.min(nowPage + 5, list.getTotalPages());//마지막페이지

        System.out.println("리스트들" + list);

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/goods/goods_list";
    }

    //공지사항 자세히보기
    @GetMapping("/goods_memo/{id}")
    public String n_memo(Model model, @PathVariable long id){
        GoodsDTO goodsDTO = goodsService.findById(id);
        model.addAttribute("notice",goodsService.findById(id));
        return "admin/notice/notice_memo";
    }

    //글추가
    @GetMapping("/goods_add")
    public String goods_add(){
        return "admin/goods/goods_write";
    }

    //글추가 처리
    @PostMapping("/goods_ok")
    public String goods_ok(@ModelAttribute GoodsDTO goodsDTO) throws IOException {
        goodsService.save(goodsDTO);
        return "redirect:/admin/goods";
    }
    //글수정
    @GetMapping("/goods_edit/{id}")
    public String n_edit(Model model, @PathVariable("id") long id){
        model.addAttribute("goods",goodsService.findById(id));
        return "admin/goods/goods_edit";
    }
    //글수정 처리
    @PostMapping("/goods_edit_ok")
    public String edit_ok(@RequestParam(name = "id") Long id,@ModelAttribute GoodsDTO goodsDTO){

        goodsService.update(id,goodsDTO);
        return "redirect:/admin/goods";
    }
    //글삭제 처리
    @GetMapping("/goods/delete")
    public String noticeDelete(@RequestParam(name = "id") Long id, Model mode){
        goodsService.goodsDelete(id);
        return "redirect:/admin/goods";
    }

}
