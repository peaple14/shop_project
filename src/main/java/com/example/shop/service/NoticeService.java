package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    public void save(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }

    public Page<NoticeEntity> noticelist(Pageable noticeDTO){//page번호와 page크기를 pageable로 전달
        return  noticeRepository.findAll(noticeDTO);
    }


    // 특정 게시글 불러오기
    public NoticeEntity noticeView(Long id){
        return noticeRepository.findById(id).get();
    }

    public void noticeDelete(Long id){
        noticeRepository.deleteById(id);
    }

    public Page<NoticeEntity> noticeSearchList(String SearchKeyword,Pageable pageable){//찾을키워드,페이지크기
        return noticeRepository.findByNoticeTitleContaining(SearchKeyword, pageable);
    }
}
