package com.example.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public class NoticeDTO {
        private Long id;
        private String notice_title;
        private String notice_name;
        private String notice_memo;
        private Long notice_view;
        private LocalDateTime noticeCreatedTime;
        private LocalDateTime noticeUpdatedTime;

    }

