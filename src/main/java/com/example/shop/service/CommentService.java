package com.example.shop.service;

import com.example.shop.dto.CommentDTO;
import com.example.shop.entity.CommentEntity;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.repository.CommentRepository;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(commentDTO.getNoticeId());
        if (optionalNoticeEntity.isPresent()) {
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, noticeEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }
}
