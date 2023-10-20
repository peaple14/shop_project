package com.example.shop.service.loginservice;

import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {

    private final MemberRepository memberRepository;


    //이메일로 아이디 찾기
    public String findIdByuserEmail(String email) {
        return memberRepository.findIdByUserEmail(email);
    }
}
