package com.example.demo.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginRequest {
    @NotBlank(message ="아이디를 입력해주세요.")
    private String account;
    @NotBlank(message ="비밀번호를 입력해주세요.")
    private String pwd;
}
