package com.example.demo.diary.dto.request;

import java.util.List;

import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;

// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Null;
// import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryCreateRequest {

  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY
  private List<AttachmentCreateRequest> attachments; // 전체 첨부(상세 조회용)


  public DiaryCreateRequest(Long mid, String title, String content, ViewScope viewScope, Emo emo, Weather weather, List<AttachmentCreateRequest> attachments) {
    this.mid = mid;
    this.title = title;
    this.content = content;
    this.viewScope = viewScope;
    this.emo = emo;
    this.weather = weather;
    this.attachments = attachments;
  }
}