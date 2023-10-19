package com.example.shop.service;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.NoticeEntity;
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
//    private final NoticeFileRepository noticeFileRepository;

    //저장용
//    @Transactional
//    public void save(NoticeDTO noticeDTO) throws IOException {
//        if(noticeDTO.getNoticeFile().isEmpty()) {
//            //첨부파일이 없다면
//            NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO);
//            noticeRepository.save(noticeEntity); //entity값으로 반환
//        } else {
//            //다중업로드 : 부모 게시판 글 등록
//            NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO); //공지사항 테이블에 글등록
//            Long savedId = noticeRepository.save(noticeEntity).getId(); //id값을 가져온다.
//            NoticeEntity notice = noticeRepository.findById(savedId).get(); //부모 entity에서 id값을 가져옴
//
//            for(MultipartFile noticeFile: noticeDTO.getNoticeFile()){
//                String originalFileName = noticeFile.getOriginalFilename(); //파일의 이름을 가져온다.
//                String storedFileName = System.currentTimeMillis() + "_" + originalFileName; //서버 저장용 이름을 생성한다. 1970.01.01
//                String savePath ="C:/shop_img/" + storedFileName; //파일의 저장경로
//                noticeFile.transferTo(new File(savePath)); //파일을 해당 경로에 저장
//
//                NoticeFileEntity noticeFileEntity = NoticeFileEntity.toNoticeFileEntity(notice, originalFileName, storedFileName);
//                noticeRepository.save(noticeFileEntity);
//            }
//
//        }
//    }

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
    public NoticeEntity noticeView(Long id){ //findbyid같은것
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
