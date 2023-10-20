package com.example.shop.dto.loginDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    //DTO나누는이유:보안이슈.쓸것만 받아오는것.

    @NotEmpty
    private String login_Id;

    @NotEmpty
    private String login_password;

    @NotEmpty
    private String login_email;
}