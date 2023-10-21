package com.example.shop.entity.notice;

import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.BaseEntity;
import com.example.shop.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notice_table")
public class NoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noticeTitle;

    @Column
    private String noticeName;

    @Column(length = 500)
    private String noticeMemo;

    @Column
    private int noticeView;

    /*파일 첨부여부 컬럼 추가*/
    @Column
    private int fileAttached; //파일 첨부의 값을 0 or 1을 받는다.


    //댓글연동 부모엔티티-자식엔티티 연결
    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();



    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeName(noticeDTO.getNoticeName());
        noticeEntity.setNoticeMemo(noticeDTO.getNoticeMemo());
        noticeEntity.setNoticeView(noticeEntity.getNoticeView());
        noticeEntity.setFileAttached(0); //파일없음
        return noticeEntity;
    }

    //더티 체킹
    public void update(String noticeTitle, String notice_name,String notice_memo){
        this.noticeTitle = noticeTitle;
        this.noticeName = notice_name;
        this.noticeMemo = notice_memo;
    }

    //파일세이브용
    public static NoticeEntity toSaveFileEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeName(noticeDTO.getNoticeName());
        noticeEntity.setNoticeMemo(noticeDTO.getNoticeMemo());
        noticeEntity.setNoticeView(noticeDTO.getNoticeView());
        noticeEntity.setFileAttached(1); //파일없음
        return noticeEntity;
    }
}
