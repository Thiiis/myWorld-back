package com.example.demo.diary.dto;

import java.util.List;

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
public class DiaryUpdateRequest {

  private Long did;
  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY
  private List<AttachmentUpdateRequest> attachments; // 전체 첨부(상세 조회용)
}
