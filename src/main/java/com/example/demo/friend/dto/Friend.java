package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

import com.example.demo.friend.enums.FriendStatus;

import lombok.Getter;

@Getter
public class Friend {
    private Long fid;
    private Long reqId;
    private Long accId;
    private String status; 
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Friend(Long reqId, Long accId) {
        this.reqId = reqId;
        this.accId = accId;
        // this.createdAt = OffsetDateTime.now().
    }
     
    public void accept() {
        this.status = FriendStatus.ACCEPTED.name();
    }
     
    public void reject() {
        this.status = FriendStatus.REJECTED.name();
    }
}
