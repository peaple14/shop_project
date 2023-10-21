package com.example.shop.service;

import com.example.shop.dto.GoodsDTO;
import com.example.shop.dto.NoticeDTO;
import com.example.shop.entity.goods.GoodsEntity;
import com.example.shop.entity.goods.GoodsFileEntity;
import com.example.shop.entity.notice.NoticeEntity;
import com.example.shop.repository.GoodsFileRepository;
import com.example.shop.repository.GoodsRepository;
import com.example.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsFileRepository goodsFileRepository;

    //저장용
    @Transactional
    public void save(GoodsDTO goodsDTO) throws IOException {
        if (goodsDTO.getGoodsFile().isEmpty()) {
            System.out.println("없을때 열루감");
            //첨부파일이 없다면
            GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goodsDTO);
            goodsRepository.save(goodsEntity); //entity값으로 반환
        } else {
            System.out.println("있을때 열루감");
            //다중 업로드 : 부모 게시판 글 등록
            GoodsEntity goodsEntity = GoodsEntity.toSaveFileEntity(goodsDTO); //공지사항 테이블에 글등록
            Long savedId = goodsRepository.save(goodsEntity).getId(); //id값을 가져온다.
            GoodsEntity goods = goodsRepository.findById(savedId).get(); //부모 entity에서 id값을 가져옴

            for (MultipartFile goodsFile : goodsDTO.getGoodsFile()) { //DTO에 담긴 파일을 가져온다.
                String originalFileName = goodsFile.getOriginalFilename(); //파일의 이름을 가져온다.
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName; //서버 저장용 이름을 생성한다.
                String savePath = "C:/shop_img/" + storedFileName; //파일의 저장경로
                goodsFile.transferTo(new File(savePath)); //파일을 해당 경로에 저장

                GoodsFileEntity goodsFileEntity = GoodsFileEntity.toGoodsFileEntity(goods, originalFileName, storedFileName);
                goodsFileRepository.save(goodsFileEntity);
            }
        }
    }

    //리스트불러오기용
    @Transactional
    public Page<GoodsEntity> goodsList(Pageable noticeDTO){//page번호와 page크기를 pageable로 전달
        return  goodsRepository.findAll(noticeDTO);
    }

    // 특정 게시글 불러오기
    @Transactional
    public GoodsDTO findById(Long id){
        Optional<GoodsEntity> optionalNoticeEntity = goodsRepository.findById(id);
        if (optionalNoticeEntity.isPresent()){
            GoodsEntity goodsEntity = optionalNoticeEntity.get();
            GoodsDTO goodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);
            return goodsDTO;

        } else {
            return null;
        }
    }

    //조회수용
    @Transactional
    public GoodsEntity getview(Long id) {
        Optional<GoodsEntity> question = this.goodsRepository.findById(id);
        if (question.isPresent()) {
            GoodsEntity goods1 = question.get();
            goods1.setGoodsView(goods1.getGoodsView()+1);
            this.goodsRepository.save(goods1);
            return goods1;
        } else {
            throw new IllegalArgumentException("Notion not found");
        }
    }

    //수정용
    @Transactional
    public void update(Long id,GoodsDTO goodsDTO) {
        GoodsEntity goodsEntity = goodsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디: " + id + " 를 찾을수 없습니다."));
        goodsEntity.update(goodsDTO.getGoodsName(),goodsDTO.getGoodsMemo(),goodsDTO.getGoodsPrice());
    }


    //삭제용
    @Transactional
    public void goodsDelete(Long id){
        goodsRepository.deleteById(id);
    }

    //검색용
    public Page<GoodsEntity> goodsSearchList(String SearchKeyword,Pageable pageable){//찾을키워드,페이지크기
        return goodsRepository.findByGoodsNameContaining(SearchKeyword, pageable);
    }


}
