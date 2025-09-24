package com.example.demo.diary.dto.response;

import java.util.Date;
import java.util.List;

import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.ViewScope;
import com.example.demo.diary.enums.Weather;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryReadResponse {
  private Long did;
  private Long mid;
  private String title;
  private String content;
  private String viewScope;
  private String emo;
  private String weather;
  private Date createdAt;
  private Date updatedAt;
  private List<AttachmentResponse> attachments;
  private AttachmentResponse representativeImage;

   // Diary → DiaryResponse 변환 생성자(create용)
  public DiaryReadResponse(Long did, Long mid, String title, String content, ViewScope viewScope, Emo emo, Weather weather, Date createdAt, Date updatedAt) {
    this.did = did;
    this.mid = mid;
    this.title = title;
    this.content = content;
    this.viewScope = viewScope.name();
    this.emo = emo.name();
    this.weather = weather.name();
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    // this.attachments = (diary.getAttachments() != null)
    //     ? diary.getAttachments().stream().map(AttachmentResponse::new).toList()
    //     : null;
    // this.representativeImage = (diary.getRepresentativeImage() != null)
    //     ? new AttachmentResponse(diary.getRepresentativeImage())
    //     : null;
  }

}
