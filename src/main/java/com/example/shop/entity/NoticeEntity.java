package com.example.shop.entity;

import com.example.shop.dto.MemberDTO;
import com.example.shop.dto.NoticeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
