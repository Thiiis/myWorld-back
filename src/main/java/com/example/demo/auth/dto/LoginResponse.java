package com.example.demo.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private String result;
    private String message;
    private String account;
    private String jwt;
    
    // 성공 시 호출할 정적 팩토리 메서드
    public static LoginResponse success(String account, String jwt) {
        return new LoginResponse("success", "로그인에 성공했습니다.", account, jwt);
    }

    // 실패 시 호출할 정적 팩토리 메서드
    public static LoginResponse fail(String message) {
        return new LoginResponse("fail", message, null, null);
    }
    
}
