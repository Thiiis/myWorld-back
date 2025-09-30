package com.example.demo.profile.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Profile {

    // 사용자 수정 불가능
    private Long pid; // PK
    private Long mid; // 멤버 FK

    // 사용자 수정 가능
    private Long jid; // 주크박스 FK
    // private Long tid; // 테마 FK

    private String nickname; // 회원가입할 때 닉네임 받아옴
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    // private Date birthdate; // 생년월일
    private String birthdate; // 생년월일
    
    private String imgName; // 프로필 이미지 파일 이름
    private String imgUrl; // 프로필 이미지 서버 저장 경로

    private String statusMessage; // 상태메세지 VARCHAR2(255)
    private String intro; // 자기소개, CLOB

    private String mainAddress; // 메인 주소
        
    public Profile(long mid, String nickname, String birthdate){
        this.mid = mid;
        this.nickname = nickname;
        this.birthdate=birthdate;
    }

}
