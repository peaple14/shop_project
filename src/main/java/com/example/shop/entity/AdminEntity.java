package com.example.shop.entity;

import com.example.shop.dto.AdminDTO;
import com.example.shop.dto.MemberDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "admin_table")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String admId;

    @Column
    private String admPass;

    @Column
    private String adName;


    public static AdminEntity toAdminEntity(AdminDTO adminDTO){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdmId(adminDTO.getAdmId());
        adminEntity.setAdmPass(adminDTO.getAdmPass());
        return adminEntity;
    }

}
