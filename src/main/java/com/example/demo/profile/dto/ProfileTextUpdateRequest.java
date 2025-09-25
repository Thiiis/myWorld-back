package com.example.demo.profile.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class ProfileTextUpdateRequest {
    private Long pid;
    private Long mid;
    private String statusMessage;
    private String intro;
    private Date updatedAt;

    public ProfileTextUpdateRequest(Long pid, String statusMessaage, String intro, Date updatedAt){
        this.pid = pid;
        this.statusMessage = statusMessaage;
        this.intro=intro;
        this.updatedAt =updatedAt;

    }
}
