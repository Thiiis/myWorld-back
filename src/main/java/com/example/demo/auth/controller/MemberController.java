package com.example.demo.auth.controller;

import java.util.List;

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
import com.example.demo.common.dto.ProfileInfo;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponse> signupMember(@RequestBody @Valid MemberSignupRequest dto) {
        memberService.signup(dto);
        MemberSignupResponse result = new MemberSignupResponse(dto.getAccount(), dto.getNickname(), dto.getEmail(),
                dto.getBirthdate());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> loginMember(@RequestBody @Valid MemberLoginRequest dto) {
        try {
            //mid를 가져오기 위한 임시 코드(나중에 한번에 수정해주세요)            
            MemberReadResponse member = memberService.getMember(dto.getAccount());
            
            // 서비스에 로그인 요청을 보내고 성공 시 JWT를 받음
            String jwt = memberService.login(dto);
            // 성공 응답 DTO 생성
            MemberLoginResponse result = new MemberLoginResponse(member.getMid(), dto.getAccount(), jwt);
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            // 로그인 실패는 401 Unauthorized 상태 코드가 더 적합
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberReadResponse> getMemberDetail(@RequestParam("account") @Valid String account) {
        MemberReadResponse result = memberService.getMember(account);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfileInfo>> searchMembers(@RequestParam("keyword") String keyword, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        List<ProfileInfo> members = memberService.searchMembers(keyword, loginMid);
        return ResponseEntity.ok(members);
    }
    
    @Login
    @PutMapping("/update")
    public ResponseEntity<String> updateMember(@RequestBody @Valid MemberUpdateRequest dto) {
        try {
            memberService.updatePwd(dto);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            // 서비스에서 발생시킨 예외를 잡아 클라이언트에게 에러 메시지를 전달
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Login
    @DeleteMapping("/delete/mid/{mid}")
    public ResponseEntity<Void> deleteMember(@PathVariable("mid") @Valid Long mid) {
        memberService.deleteByMid(mid);
        return ResponseEntity.noContent().build();
    }

}