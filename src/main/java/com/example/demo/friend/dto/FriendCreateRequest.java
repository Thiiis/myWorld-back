package com.example.demo.friend.dto;

import lombok.Getter;

@Getter
public class FriendCreateRequest {
  private Long requesterId;
  private Long accepterId;
}
