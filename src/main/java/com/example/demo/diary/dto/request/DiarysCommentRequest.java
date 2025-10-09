package com.example.demo.diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarysCommentRequest {
  //create,update시 공통적으로 필요
  private String content;
  //create시 필요(대댓글id)
  //private Long parentDcid;
  

}
