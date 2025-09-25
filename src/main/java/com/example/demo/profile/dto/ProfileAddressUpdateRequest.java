package com.example.demo.profile.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class ProfileAddressUpdateRequest {
    private Long pid;
    private Long mid;
    
    private String postalCode; // 우편번호
    private String mainAddress; // 메인 주소
    private String detailAddress; // 상세 주소, 사용자 입력값, ex. 2동 302호
    private Long latitude; // Y좌표 위도 가로
    private Long longtitude; // X좌표 경도 세로

    private Date updatedAt;

    public ProfileAddressUpdateRequest(Long pid, String postalCode, String mainAddress, String detailAddress){
        this.pid = pid;
        this.postalCode=postalCode;
        this.mainAddress=mainAddress;
        this.detailAddress=detailAddress;

    }
}
