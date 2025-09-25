package com.example.demo.auth.dto;

import java.util.Date;

import com.example.demo.profile.dto.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Member {
    private long mid;
    private Date cretedAt;
    private Date updatedAt;

    private String account;
    private String email;
    private String pwd;

    private Profile profile; // 1대1관계

    public Member(String account, String email, String pwd) {
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
