package com.example.demo.diary.dto;

import java.time.OffsetDateTime;

import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;

import lombok.Getter;

@Getter
public class DiaryRequest {
    private Long did; // GENERATED ALWAYS AS IDENTITY -> INSERT시 생략
    private OffsetDateTime createdAt; // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
    private OffsetDateTime updatedAt; // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.

    private Long mid;
    private String title;
    private String content;
    private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
    private Emo emo; // HAPPY, CALM, EXCITED, SAD
    private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY
}
