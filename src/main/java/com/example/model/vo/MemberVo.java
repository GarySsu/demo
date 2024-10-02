package com.example.model.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MemberVo {

    private Long id;
    private String username;
    private LocalDateTime createTime;

}
