package com.example.demo.diary.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarysCommentResponse {
  // 공통(Create,Read)
  private Long dcid;
  private Long did;
  private Long mid;           //보안상 mid 그대로 보내주면 안되고 mine으로 변경해주고 boolean으로 지정해서 확인용으로 하는것이 보안상 좋다.
  //private Long parentDcid; //대댓글
  private String content;
  private Date createAt;
  private Date updateAt;

}
