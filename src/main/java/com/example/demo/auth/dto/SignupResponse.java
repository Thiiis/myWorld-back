package com.example.demo.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupResponse {

    private String message;
    private String account;

    // 성공 응답 객체를 만드는 static 메서드
    public static SignupResponse success(String account) {
        return new SignupResponse("회원가입이 성공적으로 완료되었습니다.", account);
    }

    // 실패 응답 객체를 만드는 static 메서드 (예시)
    public static SignupResponse fail(String message) {
        return new SignupResponse(message, null);
    }
}
