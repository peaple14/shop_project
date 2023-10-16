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
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="notice_title")
    private String noticeTitle;

    @Column
    private String notice_name;

    @Column
    private String notice_memo;

    @Column(nullable = true)
    private String notice_wdate;


    public static NoticeEntity toNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNotice_title());
        noticeEntity.setNotice_name(noticeDTO.getNotice_name());
        noticeEntity.setNotice_memo(noticeDTO.getNotice_memo());
        return noticeEntity;
    }

    //더티 체킹
    public void update(String noticeTitle, String notice_memo){
        this.noticeTitle = noticeTitle;
        this.notice_memo = notice_memo;
    }
}
