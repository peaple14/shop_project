package com.example.shop.repository;

import com.example.shop.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    @Query("SELECT m FROM MemberEntity m WHERE m.userId = :loginId AND m.userPass = :password")
    MemberEntity findByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);


//    @Query("SELECT m.userId FROM MemberEntity m WHERE m.userEmail = :email")
//    String findIdByEmail(@Param("email") String email);


    @Query("SELECT m.userPass FROM MemberEntity m WHERE m.userId = :id AND m.userEmail = :email")
    String findPasswordByIdAndEmail(@Param("id") String id, @Param("email") String email);

    // userEmail로 userId를 찾는 메서드
    @Query("SELECT m.userId FROM MemberEntity m WHERE m.userEmail = :userEmail")
    String findUserIdByUserEmail(@Param("userEmail") String userEmail);

    Optional<MemberEntity> findByUserId(String userId);


}
