package com.example.shop.service;

import com.example.shop.dto.AdminDTO;
import com.example.shop.entity.AdminEntity;
import com.example.shop.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    public AdminDTO login(AdminDTO adminDTO) {
        /*입력된 아이디로 db에서 조회
          DB에서 조회 비밀번호와 사용자 비밀번호가 일치하는지 확인
         */
        Optional<AdminEntity> byAdmId = adminRepository.findByAdmId(adminDTO.getAdmId());
        if(byAdmId.isPresent()){//아이디 있으면
            AdminEntity adminEntity = byAdmId.get();
            if (adminEntity.getAdmPass().equals((adminDTO.getAdmPass()))) {
                AdminDTO dto = adminDTO.toadminDTO(adminEntity);
                return dto;
            } else{//비번없으면
                return null;
            }
            //조회결과없으면
        }
    return null;

    }
}
