package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class InfiniteScroll {
  private int offset;
  private int limit;
}
