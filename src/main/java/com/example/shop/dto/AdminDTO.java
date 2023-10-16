package com.example.shop.dto;

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
}
