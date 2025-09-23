package com.example.demo.auth.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    // 프로필에 저장될 정보
    private String nickname;

    // 멤버에 저장될 정보
    private String account;
    private String email;
    private String pwd;
    // 멤버에 필요 없는 정보
    private String pwdConfirm;
}
