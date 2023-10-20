package com.example.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //해야할일://"이부분가져오기" 부분 가져오기.
    //해야할일: 회원목록 나오게하고 삭제 가능하게. 로그아웃 세션제거 하기. 세션부분 좀더 손봐주기
    //해야할일:상품목록 나오고,넣고,수정,삭제 할수있게 번호(페이징)부분 잘나오게.
    //지울것:"확인용"되어있는 Sout용.

    private String resourcePath = "/upload/**"; //view접근하는 경로,임의의 저장 경로

    private String savePath = "file://C:/shop_ing"; //실제 파일 저장 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}
