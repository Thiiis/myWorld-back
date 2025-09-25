package com.example.demo.auth.dto;

import lombok.Getter;

@Getter
public class UpdateRequest {
    private Long mid;
    private String account;
    private String email;
    private String pwd;
}
