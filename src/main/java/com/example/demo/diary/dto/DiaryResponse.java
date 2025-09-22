package com.example.demo.diary.dto;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;

@Getter
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

  public DiaryResponse(Long did, Long mid, String title, String content, ViewScope viewScope, Emo emo, Weather weather, OffsetDateTime createdAt, OffsetDateTime updatedAt, List<AttachmentResponse> attachments, AttachmentResponse representativeImage) {
    this.did = did;
    this.mid = mid;
    this.title = title;
    this.content = content;
    this.viewScope = viewScope;
    this.emo = emo;
    this.weather = weather;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.attachments = attachments;
    this.representativeImage = representativeImage;
  }

    // public void setDid(Long did) { this.did = did; }
    // public void setMid(Long mid) { this.mid = mid; }
    // public void setTitle(String title) { this.title = title; }
    // public void setContent(String content) { this.content = content; }
    // public void setViewScope(ViewScope viewScope) { this.viewScope = viewScope; }
    // public void setEmo(Emo emo) { this.emo = emo; }
    // public void setWeather(Weather weather) { this.weather = weather; }
    // public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    // public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    // public void setAttachments(List<AttachmentResponse> attachments) { this.attachments = attachments; }

}
