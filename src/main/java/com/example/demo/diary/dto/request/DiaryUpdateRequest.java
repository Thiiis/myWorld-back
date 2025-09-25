package com.example.demo.diary.dto.request;

import java.util.List;

import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryUpdateRequest {

  private Long did;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY

  private List<AttachmentUpdateRequest> attachments; // 전체 첨부(상세 조회용)
  private List<Long> deleteAids;                     // 삭제할 파일 id 리스트

  public void updateRequest(Long did) {
    if (did == null) {
        throw new IllegalArgumentException("DID 값은 null일 수 없습니다.");
    }
    this.did = did;
}

  public void updateData(Long did, String title, String content, ViewScope viewScope, Emo emo, Weather weather) {
    this.did = did;
    this.title = title;
    this.content = content;
    this.viewScope = viewScope;
    this.emo = emo;
    this.weather = weather; 
  }
}
