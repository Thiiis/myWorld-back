package com.example.demo.diary.dto.response;

import java.util.List;

import com.example.demo.diary.dto.Pager;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryPageResponse {
  private Pager pager;
  private List<DiaryReadResponse> list;

  public DiaryPageResponse(Pager pager, List<DiaryReadResponse> list) {
    this.pager = pager;
    this.list = list;
  }
}
