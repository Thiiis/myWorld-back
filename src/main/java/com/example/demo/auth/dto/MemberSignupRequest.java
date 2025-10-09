package com.example.demo.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberSignupRequest {
    // 프로필에 저장될 정보
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생년월일을 YYYY-MM-DD 형식으로 입력해주세요.")
    @NotNull(message = "생년월일은 필수입니다.")
    private String birthdate;

    // 멤버에 저장될 정보
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,20}$", message = "아이디는 4~20자의 영문과 숫자를 포함해야 합니다.")
    @NotBlank(message = "아이디는 필수입니다.")
    private String account;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식대로 작성해주세요.")
    private String email;
    private String pwd;
    // 멤버에 필요 없는 정보
    private String pwdConfirm;
}
