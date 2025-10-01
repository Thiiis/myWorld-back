package com.example.demo.auth.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSignupResponse {

    private String account;
    private String nickname;
    private String email;
    private String birthdate;

    public MemberSignupResponse(String account, String nickname, String email, String birthdate) {
        this.account = account;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
    }
}
