package com.example.demo.profile.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProfileImgUpdateRequest {
    private Long pid;
    private Long mid;
    private String imgName;
    private String imgUrl;

    private Date updatedAt;

    public ProfileImgUpdateRequest(Long pid, String imgName, String imgUrl){
        this.pid=pid;
        this.imgName = imgName;
        this.imgUrl = imgUrl;

    }
}
