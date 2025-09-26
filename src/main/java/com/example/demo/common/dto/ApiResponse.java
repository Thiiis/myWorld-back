package com.example.demo.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    
    // 성공 시 호출할 정적 팩토리 메서드
    public static <T> ApiResponse<T> success(T data, String message){
        return new ApiResponse<>(200,message,data);
        
    }

    // 실패 시 호출할 정적 팩토리 메서드
    public static <T> ApiResponse <T> fail(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }
    
}
