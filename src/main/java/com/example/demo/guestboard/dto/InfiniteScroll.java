package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class InfiniteScroll {
  private Long offset;
  private Long limit;
}
