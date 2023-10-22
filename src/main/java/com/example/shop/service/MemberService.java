package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.notice.NoticeEntity;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //리스트불러오기용
    @Transactional
    public Page<MemberEntity> memberlist(Pageable memberDTO){//page번호와 page크기를 pageable로 전달
        return  memberRepository.findAll(memberDTO);
    }


    //검색용
    public Page<MemberEntity> noticeSearchList(String SearchKeyword,Pageable pageable){//찾을키워드,페이지크기
        return memberRepository.findByUserNameContaining(SearchKeyword, pageable);
    }


    //삭제용
    @Transactional
    public void memberDelete(Long id){
        memberRepository.deleteById(id);
    }


    public String idCheck(String userId) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(userId);
        if (byUserId.isPresent()){
            //결과가 있다면 : 아이디를 사용할 수 없다.
            return "no";
        } else {
            return "ok";
        }
    }

}
