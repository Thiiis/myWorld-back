package com.example.demo.diary.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

  // 그룹 정의
  public interface Create {}
  public interface Update {}

  // Create 시에는 반드시 null, Update 시에는 반드시 not null
  @Null(groups = Create.class, message = "생성 시에는 ID를 넣을 수 없습니다.")      // 생성 시에는 반드시 null이어야 함
  @NotNull(groups = Update.class, message = "수정 시에는 ID가 필요합니다.")   // 수정 시에는 반드시 있어야 함
  private Long did;

  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY

  // 클라이언트 --> 서버
  private List<AttachmentRequest> attachments; // 전체 첨부(상세 조회용)

  public DiaryRequest(Long mid, String title, String content, ViewScope viewScope, Emo emo, Weather weather, List<AttachmentRequest> attachments) {
    this.mid = mid;
    this.title = title;
    this.content = content;
    this.viewScope = viewScope;
    this.emo = emo;
    this.weather = weather;
    this.attachments = attachments;
  }

  public void setMid(Long mid) { this.mid = mid; }
  public void setTitle(String title) { this.title = title; }
  public void setContent(String content) { this.content = content; }
  public void setViewScope(ViewScope viewScope) { this.viewScope = viewScope; }
  public void setEmo(Emo emo) { this.emo = emo; }
  public void setWeather(Weather weather) { this.weather = weather; }
  public void setAttachments(List<AttachmentRequest> attachments) { this.attachments = attachments; }

}
