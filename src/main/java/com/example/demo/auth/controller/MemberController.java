package com.example.demo.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dto.MemberLoginRequest;
import com.example.demo.auth.dto.MemberLoginResponse;
import com.example.demo.auth.dto.MemberReadResponse;
import com.example.demo.auth.dto.MemberSignupRequest;
import com.example.demo.auth.dto.MemberSignupResponse;
import com.example.demo.auth.dto.MemberUpdateRequest;
import com.example.demo.auth.service.MemberService;
import com.example.demo.interceptor.Login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    
    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponse> signupMember(@Valid @RequestBody MemberSignupRequest dto) {
        memberService.signup(dto);
        MemberSignupResponse result = new MemberSignupResponse(dto.getAccount(),dto.getNickname(), dto.getEmail(), dto.getBirthdate());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> loginMember(@Valid @RequestBody MemberLoginRequest dto) {
        try {
            // 서비스에 로그인 요청을 보내고 성공 시 JWT를 받음
            String jwt = memberService.login(dto);
            // 성공 응답 DTO 생성
            MemberLoginResponse result = new MemberLoginResponse(dto.getAccount(), jwt);
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            // 로그인 실패는 401 Unauthorized 상태 코드가 더 적합        
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberReadResponse> getMemberDetail (@Valid @RequestParam("account") String account) {
        MemberReadResponse result = memberService.getMember(account);
        return ResponseEntity.ok(result);
    }

    @Login
    @PutMapping("/update")
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateRequest dto) {
        memberService.update(dto);
        return ResponseEntity.noContent().build();
    }

    @Login
    @DeleteMapping("/delete/mid/{mid}")
    public ResponseEntity<Void> deleteMember(@Valid @PathVariable("mid") Long mid) {
        memberService.deleteByMid(mid);
        return ResponseEntity.noContent().build();
    }
    

}