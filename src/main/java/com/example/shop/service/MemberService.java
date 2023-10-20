package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    //회원가입용
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    //로그인용
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()){
            //조회 결과가 있다면
            MemberEntity memberEntity = byUserId.get();
            System.out.println("조회결과엔티티에서 : " + memberEntity.getUserPass());
            System.out.println("조회결과dto에서 : " + memberDTO.getUserPass());

            if (memberEntity.getUserPass().equals(memberDTO.getUserPass())){
                //비밀번호 일치
                //entity -> dto 변환
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            //조회 결과가 없다
            return null;
        }

    }

    //잘썼는지 확인용
    public boolean isMemberDTOValid(MemberDTO memberDTO) {
        return memberDTO != null &&
                !isEmptyOrWhitespace(memberDTO.getUserId()) &&
                !isEmptyOrWhitespace(memberDTO.getUserPass()) &&
                !isEmptyOrWhitespace(memberDTO.getUserEmail());
    }
    //확인용2
    private boolean isEmptyOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }


}
