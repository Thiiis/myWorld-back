package com.example.demo.auth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.LoginResponse;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.SignupRequest;
import com.example.demo.auth.dto.SignupResponse;
import com.example.demo.auth.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signupMember(@Valid @RequestBody SignupRequest request) {
        Member member = memberService.signup(request);
        SignupResponse response = SignupResponse.success(member.getAccount());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginMember(@Valid @RequestBody LoginRequest request) {
         try {
            // 서비스에 로그인 요청을 보내고 성공 시 JWT를 받음
            String jwt = memberService.login(request);

            // 성공 응답 DTO 생성
            LoginResponse response = LoginResponse.success(request.getAccount(), jwt);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // 서비스에서 예외 발생 시(로그인 실패) 실패 응답 DTO 생성
            LoginResponse response = LoginResponse.fail(e.getMessage());
            // 로그인 실패는 401 Unauthorized 상태 코드가 더 적합
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    

    // delete 둘 중에 하나 선택, 개발하면서 생각해보기
    @DeleteMapping("/delete/mid/{mid}")
    public ResponseEntity<Void> deleteByMid(@PathVariable Long mid) {
        memberService.deleteByMid(mid);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/delete/account/{account}")
    public ResponseEntity<Void> deleteByAccount(@PathVariable String account) {
        memberService.deleteByAccount(account);
        return ResponseEntity.noContent().build();
    }


}