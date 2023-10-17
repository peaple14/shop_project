package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass //상속관계매핑
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
//    @DateTimeFormat(pattern = "yy-MM-dd") // 날짜 출력 포맷 지정
//    @JsonFormat(pattern = "yy-MM-dd") // JSON 출력 포맷 지정
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column
//    @DateTimeFormat(pattern = "yy-MM-dd") // 날짜 출력 포맷 지정
//    @JsonFormat(pattern = "yy-MM-dd") // JSON 출력 포맷 지정
    private LocalDateTime updatedTime;

}
