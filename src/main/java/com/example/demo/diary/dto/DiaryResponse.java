package com.example.demo.diary.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryResponse {
  private Long did;
  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope;
  private Emo emo;
  private Weather weather;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private List<AttachmentResponse> attachments;
  private AttachmentResponse representativeImage;

   // Diary → DiaryResponse 변환 생성자
  public DiaryResponse(Diary diary) {
    this.did = diary.getDid();
    this.mid = diary.getMid();
    this.title = diary.getTitle();
    this.content = diary.getContent();
    this.viewScope = diary.getViewScope();
    this.emo = diary.getEmo();
    this.weather = diary.getWeather();
    this.createdAt = diary.getCreatedAt();
    this.updatedAt = diary.getUpdatedAt();
    this.attachments = (diary.getAttachments() != null)
        ? diary.getAttachments().stream().map(AttachmentResponse::new).toList()
        : null;
    this.representativeImage = (diary.getRepresentativeImage() != null)
        ? new AttachmentResponse(diary.getRepresentativeImage())
        : null;
  }
}
