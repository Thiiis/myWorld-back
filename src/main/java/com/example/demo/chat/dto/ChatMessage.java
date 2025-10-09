package com.example.demo.chat.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ChatMessage {
    private Long id;
    private Long roomId;
    private Long senderId;
    private String content;
    private Date createdAt;
}
