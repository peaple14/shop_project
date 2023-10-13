package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.NoticeEntity;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    public void save(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }
}
