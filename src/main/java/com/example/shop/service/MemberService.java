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
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()){
            //조회 결과가 있다면
            MemberEntity memberEntity = byUserId.get();
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


}
