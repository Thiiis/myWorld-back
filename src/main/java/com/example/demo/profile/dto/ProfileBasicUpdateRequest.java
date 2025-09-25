package com.example.demo.profile.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class ProfileBasicUpdateRequest {
    private Long pid;
    private Long mid;
    private String nickname;
    private Date birthdate;
    // private int viewCount;
    private Date updatedAt;

    public ProfileBasicUpdateRequest(Long pid, String nickname, Date birthdate) {
        this.pid = pid;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }
}
