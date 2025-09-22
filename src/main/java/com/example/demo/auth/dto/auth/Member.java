package com.example.demo.dto.Auth;

import java.time.OffsetDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.Profile.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Member {
    private long mid;
    private OffsetDateTime cretedAt;
    private OffsetDateTime updatedAt;

    private String account;
    private String email;
    private String pwd;

    private Profile profile; //1대1관계

    public Member(SignupRequest signupRequest){
        this.account = signupRequest.getAccount();
        this.email = signupRequest.getEmail();
        this.pwd = signupRequest.getPwd();
    };

    // getter   
    public Long getMid(){
        return this.mid;
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

    @NotBlank
    @Email
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd){
    if (pwd != null && !pwd.isEmpty()) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(pwd);
        this.pwd = encodedPassword;
    }
    }
}
