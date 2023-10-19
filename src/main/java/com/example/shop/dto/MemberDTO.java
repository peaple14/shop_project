package com.example.shop.dto;

import com.example.shop.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String userId;
    private String userPass;
    private String userEmail;
    private String userName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setUserPass(memberEntity.getUserPass());
        memberDTO.setUserName(memberEntity.getUserName());
        return memberDTO;
    }
}
