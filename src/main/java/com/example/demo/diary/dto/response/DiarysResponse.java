package com.example.demo.diary.dto.response;

import java.util.Date;
import java.util.List;

import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.Weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarysResponse {
  
  private Long did;          // create
  private Long mid;          // read  - member id값
  private String title;      // create, read, update
  private String content;    // create, read, update
  private Emo emo;           // create, read, update
  private Weather weather;   // create, read, update
  private Date createdAt;    // read
  private Date updatedAt;    // read
  
  //attachment
  private List<AttachmentsResponse> attachments;  // 사진 리스트
  private AttachmentsResponse thumbnail;          // 대표 이미지
  //private Attachment thumbnail;                 // 썸네일 원본-방법1(DB에 저장-사용자가 정함)
}
