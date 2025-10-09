package com.example.demo.diary.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryComments {

  private Long dcid;
  private Long did;
  private Long mid;           //엔티티 DTO에는 굳이 mine으로 할 필요없다 왜냐하면 DB에 저장할 때 1:1로 매핑되기 때문에 컬럼명과 같으면 좋다
  //private Long parentDcid;
  private String content;
  private Date createdAt;
  private Date updatedAt;
}
