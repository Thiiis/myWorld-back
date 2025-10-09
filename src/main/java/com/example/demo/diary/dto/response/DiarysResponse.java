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
public class DiarysResponse {
  
  private Long did;          // create
  private Long mid;          // read  - member id값 / 보안상 mid 그대로 보내주면 안되고 mine으로 변경해주고 boolean으로 지정해서 확인용으로 하는것이 보안상 좋다.
  private String title;      // create, read, update
  private String content;    // create, read, update
  private Emo emo;           // create, read, update
  private Weather weather;   // create, read, update (Enum을 사용할 때에는 Response DTO로 지정해서 보내주는 것이 변환과정을 덜 거칠 수 있다 - DB에는 원시타입으로 들어가기 때문)
  private Date createdAt;    // read
  private Date updatedAt;    // read
  
  //attachment
  private List<AttachmentsResponse> attachments;  // 사진 리스트
  private AttachmentsResponse thumbnail;          // 대표 이미지 - 원래는 컬럼으로 있어야 하지만 그냥 개발자가 지정해주었다. 프론트에서도 처리 가능 (이유는 사진의 0번째로 썸네일을 지정했기 때문)
  //private Attachment thumbnail;                 // 썸네일 원본-방법1(DB에 저장-사용자가 정함)

  // 좋아요 총 개수 반환
  private int likes;        // 총 좋아요 수
  private boolean likedByMe; // 현재 로그인 사용자가 좋아요 눌렀는지 여부
}
