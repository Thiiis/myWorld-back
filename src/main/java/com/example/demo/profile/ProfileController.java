package com.example.demo.profile;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/detail")
    public String getProfile(@RequestParam String param) {
        return new String();
    }
    

    @PutMapping("/upadate")
    public void updateProfile(){

    }


}
