package com.example.shop.dto;



import com.example.shop.entity.notice.NoticeEntity;
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
        private String noticeTitle;
        private String noticeName;
        private String noticeMemo;
        private int noticeView;
        private LocalDateTime noticeCreatedTime;
        private LocalDateTime noticeUpdatedTime;;

    /*파일 업로드 추가 */
    /* 파일 1개 업로드 */
    //private MultipartFile noticeFile;
    //private String originalFileName; //원본 파일명
    //private String storedFileName; //서버 저장용 파일명
    //다중 파일 업로드
    private List<MultipartFile> noticeFile;
    private List<String> originalFileName; //원본 파일명
    private List<String> storedFileName; //서버 저장용 파일명
    private int fileAttached; //파일 첨부 여부 (첨부 : 1, 노첨부 : 0)

        //페이징용
        public NoticeDTO(Long id, String noticeTitle, String noticeName, int noticeView, LocalDateTime noticeCreatedTime) {
            this.id = id;
            this.noticeTitle = noticeTitle;
            this.noticeName = noticeName;
            this.noticeView = noticeView;
            this.noticeCreatedTime = noticeCreatedTime;
        }

        public List<MultipartFile> getNoticeFile() {//파일안올릴때 오류용
        if (noticeFile == null) {
            noticeFile = new ArrayList<>();
        }
        return noticeFile;
    }
    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity){
        NoticeDTO noticeDTO = new NoticeDTO();;
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeName(noticeEntity.getNoticeName());
        noticeDTO.setNoticeMemo(noticeEntity.getNoticeMemo());
        noticeDTO.setNoticeView(noticeEntity.getNoticeView());
        noticeDTO.setNoticeCreatedTime(noticeEntity.getCreatedTime());
        noticeDTO.setNoticeUpdatedTime(noticeEntity.getUpdatedTime());
        noticeDTO.setFileAttached(noticeEntity.getFileAttached()); //0값
        return noticeDTO;
    }

}




