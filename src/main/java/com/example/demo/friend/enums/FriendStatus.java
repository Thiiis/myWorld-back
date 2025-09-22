package com.example.demo.friend.enums;

import lombok.Getter;

@Getter
public enum FriendStatus {
  PENDING("pending"),
  ACCEPT("accept"),
  REJECT("reject");

  private final String status;

  FriendStatus(String status) {
    this.status = status;
  }  
}
