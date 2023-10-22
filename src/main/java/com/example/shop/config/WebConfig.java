package com.example.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    //지금은 못하고 나중에할일들
    //파일있을때,없을때 오류이슈
    //해야할일: 댓글부분 아마도 수정,세션부분 좀더 손봐주기,사진수정기능,ui좀더 깔끔하게(아마 안될거같음)
    //지울것:"확인용"되어있는 Sout용.
    //못하는것:네비쪽 주석처리해둔것,주문해서 카카오톡api못함.


    private String resourcePath = "/upload/**"; //view접근하는 경로,임의의 저장 경로

    private String savePath = "file:C:/shop_img/"; //실제 파일 저장 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}