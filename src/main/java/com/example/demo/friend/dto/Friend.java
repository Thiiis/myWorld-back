package com.example.demo.friend.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Friend {
    private Long fid;
    private Long reqId;
    private Long accId;
    private String status; 
    private Date createdAt;
    private Date updatedAt;

    public Friend(Long reqId, Long accId) {
        this.reqId = reqId;
        this.accId = accId;
        // this.createdAt = Date.now().
    }
}
