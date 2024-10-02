package com.example.model.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Long memberId;
    private String productName;
    private Long quantity;

}
