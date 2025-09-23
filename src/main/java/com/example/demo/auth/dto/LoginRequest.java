package com.example.demo.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String account;
    private String pwd;
}
