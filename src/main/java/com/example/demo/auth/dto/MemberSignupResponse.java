package com.example.demo.auth.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSignupResponse {

    private String account;
    private String nickname;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date birthdate;

    public MemberSignupResponse(String account, String nickname, String email, Date birthdate) {
        this.account = account;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
    }
}
