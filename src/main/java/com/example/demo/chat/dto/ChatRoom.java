package com.example.demo.chat.dto;
import java.util.Date;
import lombok.Data;

@Data
public class ChatRoom {
    private Long id;
    private String isGroup;   // 'N' 또는 'Y'
    private String status;    // ACTIVE, CLOSED 등
    private Date createdAt;
    private Date updatedAt;
}
