package com.example.demo.diary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DiaryResponse {
    private String title;
    private String message;

    // 성공 응답 객체를 만드는 static 메서드
    public static DiaryResponse success(String title) {
        return new DiaryResponse("일기가 생성되었습니다.", title);
    }

    // 실패 응답 객체를 만드는 static 메서드 (예시)
    public static DiaryResponse fail(String message) {
        return new DiaryResponse(message, null);
    }
}
