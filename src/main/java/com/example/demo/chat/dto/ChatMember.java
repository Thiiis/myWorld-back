package com.example.demo.chat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMember {
    private Long id;
    private Long roomId;
    private Long memberId;

  public ChatMember(Long roomId, Long memberId) {
        this.roomId = roomId;
        this.memberId = memberId;
    }
}
