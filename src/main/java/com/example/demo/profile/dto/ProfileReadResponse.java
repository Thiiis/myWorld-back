package com.example.demo.profile.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProfileReadResponse {
    private Long mid; // 멤버 FK
    private Long jid; // 주크박스 FK
    private Long tid; // 테마 FK

    private Date createdAt;

    private String nickname; // 회원가입할 때 닉네임 받아옴
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date birthdate; // 생년월일
    
    private String imgName; // 프로필 이미지 파일 이름
    private String imgUrl; // 프로필 이미지 서버 저장 경로

    private String statusMessage; // 상태메세지 VARCHAR2(255)
    private String intro; // 자기소개, CLOB

    // 주소
    // private String postalCode; // 우편번호
    // private String mainAddress; // 메인 주소
    // private String detailAddress; // 상세 주소, 사용자 입력값, ex. 2동 302호

    // private Long latitude; // Y좌표 위도 가로
    // private Long longtitude; // X좌표 경도 세로

}
