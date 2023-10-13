package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/shop")
    public String shop() {
        return "/shop/shop_list";
    }

    @GetMapping("/shop/detail")
    public String shopDetail() {
        return "/shop/shop_detail";
    }




}
