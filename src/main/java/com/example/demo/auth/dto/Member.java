package com.example.demo.auth.dto;

import java.time.OffsetDateTime;

import com.example.demo.profile.dto.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class Member {
    private long mid;
    private OffsetDateTime cretedAt;
    private OffsetDateTime updatedAt;

    private String account;
    private String email;
    private String pwd;

    private Profile profile; //1대1관계

    public Member(){};
    public Member(String account,String email,String pwd){
        this.account = account;
        this.email = email;
        this.pwd = pwd;
    }
    
    @NotBlank
    @Email
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }
}
