package com.example.demo.dto;

import lombok.Data;

@Data
public class User {
    private String account;
    // @NotBlank(message = "mid는 필수 사항입니다.")
   // @Size(min = 5, max = 10, message = "mid는 5자 이상 10자 이하이어야 합니다.")
    private String email;
    // @Pattern(
   //       regexp = "^010-(\\d{3}|\\d{4})-\\d{4}$",
   //       message = "전화번호는 010-xxx-xxxx 또는 010-xxxx-xxxx 형식이어야 합니다."
   // )
    private String password;
    // @NotBlank(message = "mpassword는 필수 사항입니다.")
   // @Size(min = 5, max = 10, message = "mid는 5자 이상 10자 이하이어야 합니다.")
   // @Pattern(
   //    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/]).{5,10}$",
   //    message = "비밀번호는 대소문자를 포함하고, 특수문자를 최소 1개 포함해야 합니다."
   // )
}