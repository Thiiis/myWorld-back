package com.example.demo.diary.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//DB전용 엔티티
@Getter
@NoArgsConstructor
public class Diary {

  // 클라이언트 <-- 서버
  private Long did; // GENERATED ALWAYS AS IDENTITY -> INSERT시 생략
  private OffsetDateTime createdAt; // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
  private OffsetDateTime updatedAt; // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.

  // 클라이언트 <--> 서버
  private Long mid;
  private String title;
  private String content;
  private ViewScope viewScope; // PUBLIC, FRIENDS, PRIVATE
  private Emo emo; // HAPPY, CALM, EXCITED, SAD
  private Weather weather; // SUNNY, CLOUDY, RAINY, SNOWY

  // 클라이언트 --> 서버
  private List<Attachment> attachments; // 전체 첨부(상세 조회용)
  private Attachment representativeImage; // 대표사진(리스트/페이징 조회용)

    // --- Setter 역할 메서드 ---
    public void setDid(Long did) { this.did = did; }
    public void setMid(Long mid) { this.mid = mid; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setViewScope(ViewScope viewScope) { this.viewScope = viewScope; }
    public void setEmo(Emo emo) { this.emo = emo; }
    public void setWeather(Weather weather) { this.weather = weather; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

}
