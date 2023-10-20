package com.example.shop.dto.loginDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindIdDTO {

    @NotEmpty
    private String findid_email;
}
