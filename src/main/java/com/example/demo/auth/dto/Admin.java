package com.example.demo.auth.dto;

import lombok.Data;

@Data
public class Admin {
    private Long aid;
    private String account;
    private String pwd;
    private String email;
    // 기타 필요한 필드들 (이름, 권한 등)
}