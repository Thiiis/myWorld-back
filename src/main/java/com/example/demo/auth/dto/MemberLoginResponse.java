package com.example.demo.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberLoginResponse {
    private String account;
    private String jwt;

    public MemberLoginResponse(String account, String jwt) {
        this.account = account;
        this.jwt = jwt;
    }
}
