package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

import com.example.demo.friend.enums.FriendStatus;

import lombok.Getter;

@Getter
public class Friend {
    private Long fid;
    private Long requesterId;
    private Long accepterId;
    private FriendStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Friend(Long requesterId, Long accepterId) {
        this.requesterId = requesterId;
        this.accepterId = accepterId;
    }

    public void accept() {
        this.status = FriendStatus.ACCEPTED;
    }



}
