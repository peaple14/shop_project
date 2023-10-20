package com.example.shop.dto.loginDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindPasswordDTO {

    @NotEmpty
    private String findpassword_Id;

    @NotEmpty
    private String findpassword_email;
}
