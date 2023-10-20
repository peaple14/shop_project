package com.example.shop.repository;

import com.example.shop.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
        List<CommentEntity> findCommentsByNoticeEntity_Id(Long noticeId);

}
