//package com.example.shop.notice_backup;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
//    //맨앞을 대문자로 바꾸고 써야 jpa가 인식
//    Page<NoticeEntity> findByNoticeTitleContaining(String searchKeyword, Pageable pageable);
//
//
//
//}
