package com.example.shop.dto;

import com.example.shop.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AdminDTO {
    private Long id;
    private String admId;
    private String admPass;
    private String admName;


    public static AdminDTO toadminDTO(AdminEntity adminEntity){

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(adminEntity.getId());
        adminDTO.setAdmId(adminDTO.getAdmId());
        adminDTO.setAdmPass(adminDTO.getAdmPass());
        adminDTO.setAdmName(adminEntity.getAdName());

        return adminDTO;

    }





}
