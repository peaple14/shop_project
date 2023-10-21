package com.example.shop.service;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.notice.NoticeEntity;

import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //저장용
    @Transactional
    public void save(NoticeDTO noticeDTO) throws IOException {
        //첨부파일은 상품에만.
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeEntity); //entity값으로 반환
    }



    //리스트불러오기용
    @Transactional
    public Page<NoticeEntity> noticelist(Pageable noticeDTO){//page번호와 page크기를 pageable로 전달
        return  noticeRepository.findAll(noticeDTO);
    }

    // 특정 게시글 불러오기
    @Transactional
    public NoticeDTO findById(Long id){
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if (optionalNoticeEntity.isPresent()){
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;

        } else {
            return null;
        }
    }

    //조회수용
    @Transactional
    public NoticeEntity getview(Long id) {
        Optional<NoticeEntity> question = this.noticeRepository.findById(id);
        if (question.isPresent()) {
            NoticeEntity notice1 = question.get();
            notice1.setNoticeView(notice1.getNoticeView()+1);
            this.noticeRepository.save(notice1);
            return notice1;
        } else {
            throw new IllegalArgumentException("Notion not found");
        }
    }

    //수정용
    @Transactional
    public void update(Long id,NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디: " + id + " 를 찾을수 없습니다."));
        noticeEntity.update(noticeDTO.getNoticeTitle(),noticeDTO.getNoticeName(),noticeDTO.getNoticeMemo());
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


//    //페이징 다른방식
//    public Page<NoticeDTO> paging(Pageable pageable) {
//        int page = pageable.getPageNumber()-1;
//        int pageLimit = 2; //한 페이지에 보여줄 글의 갯수
//
//        Page<NoticeEntity> noticeEntities = noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
//
//        /*getTotalElements : 전체 글 갯수;
//        getNumber : DB로 요청한 페이지번호;
//        getTotalPages : 전체 페이지 갯수
//        isFirst : 첫페이지 여부
//        isLast : 마지막 페이지 여부
//        */
//        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getId(), notice.getNoticeTitle(), notice.getNoticeName(),notice.getNoticeHit(),notice.getCreatedTime()));
//
//        return noticeDTOS;
//}

}
