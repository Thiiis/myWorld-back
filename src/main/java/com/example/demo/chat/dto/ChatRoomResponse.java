package com.example.demo.chat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChatRoomResponse {
  private Long roomId;
  private String status;
  private Long friendMid; // 상대방 MID
  private String friendName; // 상대방 이름
  private String friendProfileImg; // 상대방 프로필 이미지
  private String lastMessage; // 마지막 메시지
  private Date lastMessageTime; // 마지막 메시지 시간
  private Date createdAt;
  private int unreadCount;
}
