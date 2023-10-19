package com.example.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String CommentMemo;
    private Long noticeId;
    private LocalDateTime commentCreatedTime;

}
