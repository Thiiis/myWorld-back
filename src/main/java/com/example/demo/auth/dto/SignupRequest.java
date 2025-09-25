package com.example.demo.auth.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class SignupRequest {
    // 프로필에 저장될 정보
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date birthdate;

    // 멤버에 저장될 정보
    private String account;
    private String email;
    private String pwd;
    // 멤버에 필요 없는 정보
    private String pwdConfirm;
}
