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
    private String user_id;

    @Column
    private String user_pass;

    @Column
    private String user_email;

    @Column
    private String user_name;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUser_id(memberDTO.getUser_id());
        memberEntity.setUser_pass(memberDTO.getUser_pass());
        memberEntity.setUser_email(memberDTO.getUser_email());
        memberEntity.setUser_name(memberDTO.getUser_name());
        return memberEntity;
    }
}
