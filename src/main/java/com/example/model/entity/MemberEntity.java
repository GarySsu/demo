package com.example.model.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MemberEntity {

    private Long id;
    private String username;
    private LocalDateTime createTime;

}
