package com.example.demo.auth.dto;

import lombok.Data;

@Data
public class MemberReadResponse {
    private Long mid;
    private String account;
    private String email;

    public MemberReadResponse(Long mid, String account, String email) {
        this.mid = mid;
        this.account = account;
        this.email = email;
    }
}
