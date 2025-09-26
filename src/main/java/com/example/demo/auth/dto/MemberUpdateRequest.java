package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdateRequest {
    private Long mid;
    private String account;
    @Email
    private String email;
    private String pwd;
}
