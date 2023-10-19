package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice_file_table")
public class NoticeFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeEntity noticeEntity;

    public static NoticeFileEntity toNoticeFileEntity(NoticeEntity noticeEntity, String originalFileName, String storedFileName){
        NoticeFileEntity noticeFileEntity = new NoticeFileEntity();
        noticeFileEntity.setOriginalFileName(originalFileName);
        noticeFileEntity.setStoredFileName(storedFileName);
        noticeFileEntity.setNoticeEntity(noticeEntity);
        return noticeFileEntity;
    }
}
