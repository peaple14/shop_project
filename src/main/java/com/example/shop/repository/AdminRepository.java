package com.example.shop.repository;

import com.example.shop.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    //select * from admin_table where admid = ""
    Optional<AdminEntity> findByAdmId(String admId);
}
