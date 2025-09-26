package com.example.demo.auth.dto;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberReadResponse {
    private String account;
    private String email;

    // private String nickname;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    // private Data birthdate;

}
