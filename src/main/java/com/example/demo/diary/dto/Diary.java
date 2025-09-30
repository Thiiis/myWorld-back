package com.example.demo.diary.dto;

import java.util.Date;

import lombok.Data;

//DB전용 엔티티
@Data
public class Diary {

    private Long did;                       // GENERATED ALWAYS AS IDENTITY -> INSERT시 생략
    private Long mid;
    private String title;
    private String content;
    private String emo;                     // HAPPY, CALM, EXCITED, SAD
    private String weather;                 // SUNNY, CLOUDY, RAINY, SNOWY
    private Date createdAt;                 // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
    private Date updatedAt;                 // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.
    
}

