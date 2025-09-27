package com.example.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginResponse {
    private String account;
    private String jwt;

}
