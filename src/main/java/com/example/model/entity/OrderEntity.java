package com.example.model.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderEntity {

    private String orderId;
    private Long memberId;
    private String username;
    private String productName;
    private Long quantity;
    private LocalDateTime createTime;

}
