package com.example.shop.repository;

import com.example.shop.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
}
