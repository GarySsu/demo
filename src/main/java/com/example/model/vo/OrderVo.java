package com.example.model.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderVo {

    private String orderId;
    private Long memberId;
    private String username;
    private String productName;
    private Long quantity;
    private LocalDateTime createTime;

}
