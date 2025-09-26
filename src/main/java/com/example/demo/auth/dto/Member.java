package com.example.demo.auth.dto;

import java.util.Date;

import com.example.demo.profile.dto.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Member {
    private Long mid;
    private Date cretedAt;
    private Date updatedAt;

    private String account;
    private String email;
    private String pwd;

    private Profile profile; // 1대1관계

    // Create
    public Member(String account, String email, String pwd) {
        this.account = account;
        this.email = email;
        this.pwd = pwd;
    }

    // Update
    public Member(Long mid, String email, String pwd){
        this.mid = mid;
        this.email = email;
        this.pwd = pwd;
    }
}
