package com.example.demo.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberLoginResponse {
    private Long mid;
    private String account;
    private String jwt;

    public MemberLoginResponse(Long mid, String account, String jwt) {
        this.mid = mid;
        this.account = account;
        this.jwt = jwt;
    }
}
