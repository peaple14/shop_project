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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //저장용
    @Transactional
    public void save(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }

    //수정용
    @Transactional
    public void update(Long id,NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디: " + id + " 를 찾을수 없습니다."));
        noticeEntity.update(noticeDTO.getNotice_title(),noticeDTO.getNotice_name(),noticeDTO.getNotice_memo());
    }

    //리스트불러오기용
    public Page<NoticeEntity> noticelist(Pageable noticeDTO){//page번호와 page크기를 pageable로 전달
        return  noticeRepository.findAll(noticeDTO);
    }


    // 특정 게시글 불러오기
    public NoticeEntity noticeView(Long id){
        return noticeRepository.findById(id).get();
    }

    //삭제용
    @Transactional
    public void noticeDelete(Long id){
        noticeRepository.deleteById(id);
    }

    //검색용
    public Page<NoticeEntity> noticeSearchList(String SearchKeyword,Pageable pageable){//찾을키워드,페이지크기
        return noticeRepository.findByNoticeTitleContaining(SearchKeyword, pageable);
    }


    //조회수증가용
    public NoticeEntity getview(Long id) {
        Optional<NoticeEntity> question = this.noticeRepository.findById(id);
        if (question.isPresent()) {
            NoticeEntity notice1 = question.get();
            notice1.setNotice_view(notice1.getNotice_view()+1);
            this.noticeRepository.save(notice1);
            return notice1;
        } else {
            throw new IllegalArgumentException("Notion not found");
        }
    }


}
