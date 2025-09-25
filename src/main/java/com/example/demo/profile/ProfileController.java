package com.example.demo.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @PatchMapping("/text")
    public void updateText(){

    }


}
