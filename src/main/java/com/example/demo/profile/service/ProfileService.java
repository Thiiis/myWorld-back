package com.example.demo.profile.service;

import org.springframework.stereotype.Service;

import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileAddressUpdateRequest;
import com.example.demo.profile.dto.ProfileTextUpdateRequest;
import com.example.demo.profile.dto.ProfileUpdateRequest;

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

    public Profile getProfileByNickname(String nickname) {
        return profileDao.selectByNickname(nickname);
    }

   // '텍스트' 업데이트 요청 처리
    public void updateText(ProfileTextUpdateRequest textDto) {
        // 1. Mapper로 보낼 '통합 Request' 생성
        ProfileUpdateRequest unifiedRequest = new ProfileUpdateRequest();

        // 2. 필수 값(pid)과 섹션에 맞는 값(text)을 통합 Request에 복사
        unifiedRequest.setPid(textDto.getPid());
        unifiedRequest.setStatusMessage(textDto.getStatusMessage());
        unifiedRequest.setIntro(textDto.getIntro());

        // 3. 통합 Request를 Mapper의 단일 update 메소드로 전달
        profileDao.update(unifiedRequest);
    }

    // '주소' 업데이트 요청 처리
    public void updateAddress(ProfileAddressUpdateRequest addressDto) {
        // 1. Mapper로 보낼 '통합 Request' 생성
        ProfileUpdateRequest unifiedDto = new ProfileUpdateRequest();
        
        // 2. 필수 값(pid)과 섹션에 맞는 값(address)을 통합 Request에 복사
        unifiedDto.setPid(addressDto.getPid());
        unifiedDto.setPostalCode(addressDto.getPostalCode());
        unifiedDto.setMainAddress(addressDto.getMainAddress());
        unifiedDto.setDetailAddress(addressDto.getDetailAddress());

        // 3. 여기서도 동일한 updateProfile 메소드를 호출
        profileDao.update(unifiedDto);
    }
}
