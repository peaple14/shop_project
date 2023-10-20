package com.example.shop.service;

import com.example.shop.dto.CommentDTO;
import com.example.shop.entity.CommentEntity;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.repository.CommentRepository;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;

    //댓글저장
    @Transactional
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

    // 댓글 목록
    @Transactional
    public List<CommentDTO> getCommentsByNoticeId(Long noticeId) {
        List<CommentEntity> commentEntities = commentRepository.findCommentsByNoticeEntity_Id(noticeId);
        List<CommentDTO> commentDTOs = commentEntities.stream()
                .map(commentEntity -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentWriter(commentEntity.getCommentWriter());
                    commentDTO.setCommentMemo(commentEntity.getCommentMemo());
                    commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
                    return commentDTO;
                })
                .collect(Collectors.toList());

        return commentDTOs;
    }


}
