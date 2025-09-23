package com.example.demo.diary.dto;

import java.time.OffsetDateTime;
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

  private Long did; // GENERATED ALWAYS AS IDENTITY -> INSERT시 생략
  private OffsetDateTime createdAt; // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
  private OffsetDateTime updatedAt; // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.

  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY
  private List<Attachment> attachments; // 전체 첨부(상세 조회용)
  private Attachment representativeImage; // 대표사진(리스트/페이징 조회용)


    // // 방법 1(캡슐화를 지키기 위한 정적 팩토리 사용)
    //  public void update(DiaryUpdateRequest req) {
    //     if (req.getTitle() != null) this.title = req.getTitle();
    //     if (req.getContent() != null) this.content = req.getContent();
    //     if (req.getWeather())
    //  }
  
    // --- 도메인 행위 메서드 --- 방법2
    public void updateTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("제목은 비어 있을 수 없음");
        this.title = title;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateContent(String content) {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("내용은 비어 있을 수 없음");
        this.content = content;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateViewScope(ViewScope viewScope) {
        if (viewScope == null) throw new IllegalArgumentException("공개 범위는 비어 있을 수 없음");
        this.viewScope = viewScope;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateEmo(Emo emo) {
        if (emo == null) throw new IllegalArgumentException("이모티콘은 비어 있을 수 없음");
        this.emo = emo;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateWeather(Weather weather) {
        if (weather == null) throw new IllegalArgumentException("날씨는 비어 있을 수 없음");
        this.weather = weather;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateRepresentativeImage(Attachment representativeImage) {
        this.representativeImage = representativeImage;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateMid(Long mid) {
        if (mid == null) throw new IllegalArgumentException("작성자 ID는 비어 있을 수 없음");
        this.mid = mid;
        this.updatedAt = OffsetDateTime.now();
    }

    public void updateCreatedAt(OffsetDateTime createdAt) {
        if (createdAt == null) throw new IllegalArgumentException("생성일은 null일 수 없음");
        this.createdAt = createdAt;
    }

    public void updateUpdatedAt(OffsetDateTime updatedAt) {
        if (updatedAt == null) throw new IllegalArgumentException("수정일은 null일 수 없음");
        this.updatedAt = updatedAt;
    }
 }
