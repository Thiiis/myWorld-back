package com.example.demo.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.dto.ApiResponse;
import com.example.demo.interceptor.Login;
import com.example.demo.profile.dto.ProfileReadResponse;
import com.example.demo.profile.dto.ProfileUpdateRequest;
import com.example.demo.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @Login
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<ProfileReadResponse>> getProfileDetail(@RequestParam("pid") Long pid) {
        ProfileReadResponse result = profileService.getProfile(pid);
        return ResponseEntity.ok(ApiResponse.success(result, null));
    }

    @Login
    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileUpdateRequest dto) {
        profileService.update(dto);
        return ResponseEntity.ok("수정이 완료되었습니다.");
    }


}
