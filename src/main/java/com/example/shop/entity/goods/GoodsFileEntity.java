package com.example.shop.entity.goods;

import com.example.shop.entity.BaseEntity;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "goods_file_table")
public class GoodsFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private GoodsEntity goodsEntity;

    //파일 있을시
    public static GoodsFileEntity toGoodsFileEntity(GoodsEntity goodsEntity, String originalFileName, String storedFileName){
        GoodsFileEntity goodsFileEntity = new GoodsFileEntity();
        goodsFileEntity.setOriginalFileName(originalFileName);
        goodsFileEntity.setStoredFileName(storedFileName);
        goodsFileEntity.setGoodsEntity(goodsEntity);
        return goodsFileEntity;
    }
}
