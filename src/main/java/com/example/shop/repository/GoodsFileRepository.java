package com.example.shop.repository;

import com.example.shop.entity.goods.GoodsFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsFileRepository extends JpaRepository<GoodsFileEntity, Long> {
}
