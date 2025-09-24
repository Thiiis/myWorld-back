package com.example.demo.profile.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.auth.dto.Member;

import lombok.Getter;

@Getter
public class Profile {

    private Long pid; // PK
    private Long mid; // 멤버 FK
    private Long jid; // 주크박스 FK

    private Date createdAt;
    private Date updatedAt;

    private Member member; //1대1관계
    
    private String nickname; // 회원가입할 때 닉네임 받아옴
    private Date birthdate; // 생년월일
    
    private String imgName; // 프로필 이미지 파일 이름
    private String imgUrl; // 프로필 이미지 서버 저장 경로

    private String statusMessage; // 상태메세지 VARCHAR2(255)
    private String intro; // 자기소개, CLOB

    // 주소
    private String postalCode; // 우편번호
    private String mainAddress; // 메인 주소
    private String detailAddress; // 상세 주소, 사용자 입력값, ex. 2동 302호
    private Long latitude; // Y좌표 위도 가로
    private Long longtitude; // X좌표 경도 세로

    private int viewCount  = 0; // 미니홈 조회수
    
    private String viewScope;
    // Profile Privacy가 있긴 한데 어떻게 할지 몰겟음
    
    // Setter
    public String upDateNickname(String nickname){
        this.nickname = nickname;
        return nickname;
    }
    public Date upDateBirthDate(Date birthDate){
        this.birthdate = birthdate;
        return this.birthdate;
    }
    public Map<String, String> updateImageInfo(String newName){
        Map<String, String> map = new HashMap<String,String>();
        this.imgName= newName;
        // this.imgUrl에서 파일 이름만 바꾸면 되는디....
        
        map.put("name",this.imgName);
        map.put("url",this.imgUrl);
        
        return map;
    }
    public Map<String, String> updateTextInfo(String statusMessage, String intro){
        this.statusMessage = statusMessage;
        this.intro = intro;

        Map<String,String> map = new HashMap<String,String>();
        map.put("statusMessage",this.statusMessage);
        map.put("intro",this.intro);

        return map;
    }

    //주소 게터 세터 나중에

}
