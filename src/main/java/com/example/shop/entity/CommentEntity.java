package com.example.shop.entity;

import com.example.shop.dto.CommentDTO;
import com.example.shop.entity.notice.NoticeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String commentWriter;

    @Column(length = 500)
    private String commentMemo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeEntity noticeEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, NoticeEntity noticeEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentMemo(commentDTO.getCommentMemo());
        commentEntity.setNoticeEntity(noticeEntity);
        return commentEntity;
    }
}
