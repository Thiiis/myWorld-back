package com.example.demo.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class MemberUpdateRequest {
    @NotBlank
    private String currentPwd;
    @NotBlank
    private String newPwd;
    @NotBlank
    private String newPwdConfirm;
}
