package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class MemberUpdateRequest {
    private Long mid;
    @Email
    private String email;
    private String pwd;
}
