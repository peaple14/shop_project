package com.example.shop.repository;

import com.example.shop.entity.goods.GoodsEntity;
import com.example.shop.entity.notice.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {


    Page<GoodsEntity> findByGoodsNameContaining(String searchKeyword, Pageable pageable);
}
