package com.example.demo.profile.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileReadRequest {
    private Long pid; // PK
    private Long mid; // 멤버 FK
    private String nickname; // 회원가입할 때 닉네임 받아옴

}
