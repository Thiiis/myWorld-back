package com.example.demo.diary.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;

import lombok.Getter;
import lombok.NoArgsConstructor;

//DB전용 엔티티
@Getter
@NoArgsConstructor
public class Diary {

    private Long did;                       // GENERATED ALWAYS AS IDENTITY -> INSERT시 생략
    private Long mid;
    private String title;
    private String content;
    private ViewScope viewScope;            // PUBLIC, FRIENDS, PRIVATE
    private Emo emo;                        // HAPPY, CALM, EXCITED, SAD
    private Weather weather;                // SUNNY, CLOUDY, RAINY, SNOWY
    private Date createdAt;                 // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
    private Date updatedAt;                 // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.
    
    private List<Attachment> attachments;   // 전체 첨부(상세 조회용)
    private Attachment representativeImage; // 대표사진(리스트/페이징 조회용)

     // 방법 1(캡슐화를 지키기 위한 정적 팩토리 사용) // public void update(DiaryUpdateRequest req) { // if (req.getTitle() != null) this.title = req.getTitle(); // }

    public Diary(Long mid, String title, String content,
            ViewScope viewScope, Emo emo, Weather weather) {
        this.mid = mid;
        this.title = title;
        this.content = content;
        this.viewScope = viewScope;
        this.emo = emo;
        this.weather = weather;
    }

    // --- 도메인 행위 메서드 --- 방법2
    public void updateAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void updateRepresentativeImage(Attachment representativeImage) {
        this.representativeImage = representativeImage;
    }
}
