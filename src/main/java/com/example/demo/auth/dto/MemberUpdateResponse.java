package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberUpdateResponse {
    private Long mid;
    private String account;
    @Email
    private String email;
    private String pwd;
}
