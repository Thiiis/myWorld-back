package com.example.demo.profile.service;

import org.springframework.stereotype.Service;

import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileDao profileDao;

    public Profile getProfileByPid(Long pid) {
        return profileDao.selectByPid(pid);
    }
    public Profile getProfileByMid(Long mid) {
        return profileDao.selectByMid(mid);
    }
    // public void update(ProfileUpdateRequest request){
    //     profileDao.update(request);
    // }
}
