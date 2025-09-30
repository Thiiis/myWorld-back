package com.example.demo.diary.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryComment {

  private Long dcid;
  private Long did;
  private Long mid;
  private Long parentDcid;
  private String content;
  private Date createAt;
  private Date updateAt;

}
