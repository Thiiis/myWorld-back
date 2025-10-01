package com.example.demo.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberSignupRequest {
    // 프로필에 저장될 정보
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;
    @NotNull(message = "생년월일은 필수입니다.")
    private String birthdate;

    // 멤버에 저장될 정보
    @NotBlank(message = "아이디는 필수입니다.")
    private String account;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식대로 작성해주세요.")
    private String email;
    private String pwd;
    // 멤버에 필요 없는 정보
    private String pwdConfirm;
}
