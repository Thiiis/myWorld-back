package com.example.demo.dto.Auth;

public class SignupRequest {
    // 프로필에 저장될 정보
    private String nickname;

    // 멤버에 저장될 정보
    private String account;
    private String email;
    private String pwd;

    public String getNickname(){
        return this.nickname;
    }
    public String getAccount(){
        return this.account;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPwd(){
        return this.pwd;
    }
}
