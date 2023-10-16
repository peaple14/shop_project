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

    //    @Builder https://velog.io/@byeongju/JPA-Failed-to-create-query-for-method-public-abstract
//    private Notification(NotificationType notificationType, Member member, Post post, Comment comment) {
//        this.notificationType = notificationType;
//        this.member = member;
//        this.post = post;
//        this.comment = comment;
//        this.inquired = false;
//    } 이런식으로 builder로 변경하기
    public static NoticeEntity toNoticeEntity(NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeTitle(noticeDTO.getNotice_title());
        noticeEntity.setNotice_name(noticeDTO.getNotice_name());
        noticeEntity.setNotice_memo(noticeDTO.getNotice_memo());
        return noticeEntity;

    }
}
