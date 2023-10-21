package com.example.shop.dto;


import com.example.shop.entity.goods.GoodsEntity;
import com.example.shop.entity.goods.GoodsFileEntity;
import com.example.shop.entity.notice.NoticeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GoodsDTO {
    private Long id;
    private String goodsName;
    private String goodsMemo;
    private Long goodsPrice;
    private int goodsView;
    private LocalDateTime goodsCreatedTime;
    private LocalDateTime goodsUpdatedTime;;

    /*파일 업로드 추가 */
    /* 파일 1개 업로드 */
    //private MultipartFile noticeFile;
    //private String originalFileName; //원본 파일명
    //private String storedFileName; //서버 저장용 파일명
    //다중 파일 업로드
    private List<MultipartFile> goodsFile;
    private List<String> originalFileName; //원본 파일명
    private List<String> storedFileName; //서버 저장용 파일명
    private int fileAttached; //파일 첨부 여부 (첨부 : 1, 노첨부 : 0)




    public static GoodsDTO toGoodsDTO(GoodsEntity goodsEntity){
        GoodsDTO goodsDTO = new GoodsDTO();;
        goodsDTO.setId(goodsEntity.getId());
        goodsDTO.setGoodsName(goodsEntity.getGoodsName());
        goodsDTO.setGoodsMemo(goodsEntity.getGoodsMemo());
        goodsDTO.setGoodsPrice(goodsEntity.getGoodsPrice());
        goodsDTO.setGoodsView(goodsEntity.getGoodsView());
        goodsDTO.setGoodsCreatedTime(goodsEntity.getCreatedTime());
        goodsDTO.setGoodsUpdatedTime(goodsEntity.getUpdatedTime());
        if(goodsEntity.getFileAttached() == 0){
            goodsDTO.setFileAttached(goodsEntity.getFileAttached()); //0값
        } else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            goodsDTO.setFileAttached(goodsEntity.getFileAttached());

            for(GoodsFileEntity goodsFileEntity:goodsEntity.getGoodsFileEntityList()){
                originalFileNameList.add(goodsFileEntity.getOriginalFileName());
                storedFileNameList.add(goodsFileEntity.getStoredFileName());
            }
            goodsDTO.setOriginalFileName(originalFileNameList); //
            goodsDTO.setStoredFileName(storedFileNameList);

        }
        return goodsDTO;
    }

}




