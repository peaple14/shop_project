package com.example.shop.dto;


import com.example.shop.entity.NoticeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public class NoticeDTO {
        private Long id;
        private String notice_title;
        private String notice_name;
        private String notice_memo;
        private Long notice_view;
        private LocalDateTime noticeCreatedTime;
        private LocalDateTime noticeUpdatedTime;;

    /*파일 업로드 추가 */
    //private MultipartFile noticeFile;
    //private String originalFileName; //원본 파일명
    //private String storedFileName; //서버 저장용 파일명

    private List<MultipartFile> noticeFile;
    private List<String> originalFileName;//원본 파일명
    private List<String> storedFileName;//서버 저장용 파일명
    private int fileAttached; //파일 첨부 여부 (첨부 : 1, 노첨부 : 0)

        public static NoticeDTO toNoticeDto(NoticeEntity noticeEntity){
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setId(noticeDTO.getId());
            noticeDTO.setNotice_title(noticeDTO.notice_title);
            noticeDTO.setNotice_name(noticeDTO.notice_name);
            noticeDTO.setNotice_memo(noticeDTO.notice_memo);
            noticeDTO.setNotice_view(noticeDTO.notice_view);
            noticeDTO.setNoticeCreatedTime(noticeDTO.noticeCreatedTime);
            noticeDTO.setNoticeUpdatedTime(noticeDTO.noticeUpdatedTime);
//            if (noticeEntity.getFileAttached() == 0) {
//                noticeDTO.setFileAttached(noticeEntity.getFileAttached());//0값
//            } else{
//                List<String> originalFileNameList = new ArrayList<>();
//                List<String> storedFileNameList = new ArrayList<>();
//                noticeDTO.setFileAttached(noticeEntity.getFileAttached());
//
//                for(NoticeFileEntity : noticeEntity.getNoticeFileEntityList()){
//                    originalFileNameList.add(noticeFileEntity.getOriginalFileName());
//                    storedFileNameList.add(noticeFileEntity.getStoredFileName());
//                }
//                noticeDTO.setOriginalFileName(originalFileNameList);
//                noticeDTO.setStoredFileName(storedFileNameList);
//
//            }
            return noticeDTO;
        }

    }

