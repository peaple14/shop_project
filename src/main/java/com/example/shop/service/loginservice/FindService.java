package com.example.shop.service.loginservice;

import com.example.shop.entity.MemberEntity;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {

    private final MemberRepository memberRepository;


    //이메일로 아이디 찾기
    public String findUserIdByUserEmail(String email) {
        MemberEntity memberEntity = memberRepository.findUserIdByUserEmail(email);
        System.out.println("이건가" + memberEntity);
        String userId = "";
        if (memberEntity != null) {
            // memberEntity가 null이 아닌 경우에만 사용합니다.
            userId = memberEntity.getUserId();
            System.out.println("여기다" + userId);
            // 나머지 로직 수행
        } else {
            System.out.println("실패");
            return null;
        }
        return userId;
    }
}
