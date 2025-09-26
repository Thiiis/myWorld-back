package com.example.demo.auth.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberReadResponse {
    private String account;
    private String email;
    private String nickname; // 프로필 연동
    private Date birthdate;  // 프로필 연동
}
