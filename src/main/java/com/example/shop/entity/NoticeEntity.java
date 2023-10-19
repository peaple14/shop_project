package com.example.shop.entity;

import com.example.shop.dto.MemberDTO;
import com.example.shop.dto.NoticeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notice_table")
public class NoticeEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="notice_title")
    private String noticeTitle;

    @Column
    private String notice_name;

    @Column(length = 500)
    private String notice_memo;



    @Column(columnDefinition = "Long default 0", nullable = false)
    private int notice_view = 0; // 기본값을 0으로 초기화

//    /*파일 첨부여부 컬럼 추가*/
//    @Column
//    private int fileAttached; //파일 첨부의 값을 0 or 1을 받는다.
//
//
//    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<NoticeFileEntity> noticeFileEntityList = new ArrayList<>(); //

    //댓글연동 부모엔티티-자식엔티티 연결
    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();




    public static NoticeEntity toNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNotice_title());
        noticeEntity.setNotice_name(noticeDTO.getNotice_name());
        noticeEntity.setNotice_memo(noticeDTO.getNotice_memo());
        noticeEntity.setNotice_view(noticeEntity.getNotice_view());
        return noticeEntity;
    }

    //더티 체킹
    public void update(String noticeTitle, String notice_name,String notice_memo){
        this.noticeTitle = noticeTitle;
        this.notice_name = notice_name;
        this.notice_memo = notice_memo;
    }
}
