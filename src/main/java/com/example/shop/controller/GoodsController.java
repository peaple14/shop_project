package com.example.shop.controller;

import com.example.shop.dto.GoodsDTO;
import com.example.shop.entity.goods.GoodsEntity;
import com.example.shop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class GoodsController {




    //수정html만들기 , 검색쪽 에러 물어보기
    private final GoodsService goodsService;

    //상품리스트
    @GetMapping("/goods")
    public String goods(Model model,
                         @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword) {
        Page<GoodsEntity> list = null;


        if(searchKeyword == null) {//검색어 없을때
            list = goodsService.goodsList(pageable);
        }else {//검색어 있을때
            list = goodsService.goodsSearchList(searchKeyword, pageable);//검색어와 함께
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

        return "/goods/goods_list";
    }

    //상품상세
    @GetMapping("/goods_memo/{id}")
    public String g_memo( @PathVariable long id,Model model){
        goodsService.getview(id);//조회수증가용
        GoodsDTO goodsDTO = goodsService.findById(id);
        model.addAttribute("goods",goodsDTO);
        System.out.println("굿즈 상세 테스트용:" + goodsDTO);
        return "goods/goods_detail";
    }
















}
