package com.example.shop.entity.goods;

import com.example.shop.dto.GoodsDTO;
import com.example.shop.entity.BaseEntity;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "goods_table")
public class GoodsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String goodsName;

    @Column
    private long goodsPrice;

    @Column(length = 500)
    private String goodsMemo;

    @Column
    private int goodsView;

    /*파일 첨부여부 컬럼 추가*/
    @Column
    private int fileAttached; //파일 첨부의 값을 0 or 1을 받는다.

    /*파일첨부 연동*/
    @OneToMany(mappedBy = "goodsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsFileEntity> goodsFileEntityList = new ArrayList<>(); //




    public static GoodsEntity toSaveEntity(GoodsDTO goodsDTO){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsName(goodsDTO.getGoodsName());
        goodsEntity.setGoodsPrice(goodsDTO.getGoodsPrice());
        goodsEntity.setGoodsMemo(goodsDTO.getGoodsMemo());
        goodsEntity.setGoodsView(goodsEntity.getGoodsView());
        goodsEntity.setFileAttached(0); //파일없음
        return goodsEntity;
    }

    //더티 체킹
    public void update( String goods_name,String goods_memo,Long goods_price){
        this.goodsName = goods_name;
        this.goodsPrice = goods_price;
        this.goodsMemo = goods_memo;
    }

    //파일세이브용
    public static GoodsEntity toSaveFileEntity(GoodsDTO goodsDTO) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsName(goodsDTO.getGoodsName());
        goodsEntity.setGoodsPrice(goodsDTO.getGoodsPrice());
        goodsEntity.setGoodsMemo(goodsDTO.getGoodsMemo());
        goodsEntity.setGoodsView(goodsDTO.getGoodsView());
        goodsEntity.setFileAttached(1); //파일있음
        return goodsEntity;
    }
}
