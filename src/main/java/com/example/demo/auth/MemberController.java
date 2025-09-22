package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Auth.LoginRequest;
import com.example.demo.dto.Auth.LoginResponse;
import com.example.demo.dto.Auth.Member;
import com.example.demo.dto.Auth.SignupRequest;
import com.example.demo.dto.Auth.SignupResponse;
import com.example.demo.interceptor.Login;
import com.example.demo.service.JwtService;
import com.example.demo.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signupMember(@Valid @RequestBody SignupRequest signupRequest) {
        Member member = memberService.signup(signupRequest);
        SignupResponse response = SignupResponse.success(member.getAccount());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


}