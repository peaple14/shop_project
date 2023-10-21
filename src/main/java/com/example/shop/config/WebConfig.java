package com.example.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //지금할거:상품목록 수정기능
    //로그인했을때 로그인부분네비에서 없애고 이름뜨게하기
    //해야할일:세부설정만 사진가져오기
    //해야할일: 회원목록 나오고 삭제가능하게


    //지금은 못하고 나중에할일들
    //파일있을때,없을때 오류이슈
    //해야할일://"이부분가져오기" 부분 가져오기.
    //해야할일: 댓글부분 아마도 수정,세션부분 좀더 손봐주기,
    //지울것:"확인용"되어있는 Sout용.
    //못하는것:주문목록못함,주문해서 카카오톡api못함.
    

    private String resourcePath = "/upload/**"; //view접근하는 경로,임의의 저장 경로

    private String savePath = "file://C:/shop_ing"; //실제 파일 저장 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}
