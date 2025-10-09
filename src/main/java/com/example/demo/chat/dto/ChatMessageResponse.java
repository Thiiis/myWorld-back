package com.example.demo.chat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChatMessageResponse {
    private Long senderId;
    private String senderAccount;
    private String senderNickname;
    private String serderImgUrl;
    private String content;
    private Date createdAt;
}
