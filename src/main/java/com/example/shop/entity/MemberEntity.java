package com.example.shop.entity;

import com.example.shop.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String userId;

    @Column
    private String userPass;

    @Column
    private String userEmail;

    @Column
    private String userName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setUserPass(memberDTO.getUserPass());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserName(memberDTO.getUserName());
        return memberEntity;
    }
}
