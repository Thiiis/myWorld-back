package com.example.demo.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileAddressUpdateRequest;
import com.example.demo.profile.dto.ProfileBasicUpdateRequest;
import com.example.demo.profile.dto.ProfileFKUpdateRequest;
import com.example.demo.profile.dto.ProfileImgUpdateRequest;
import com.example.demo.profile.dto.ProfileTextUpdateRequest;
import com.example.demo.profile.dto.ProfileUpdateRequest;

@Mapper
 public interface ProfileDao {
     void insert(Profile profile); //Create

     // Read
     //  int existsByNickname(String nickname);
     Profile selectByPid(Long pid);
     Profile selectByMid(Long mid);
     Profile selectByNickname(String nickname);
    // Update
     void update(ProfileUpdateRequest dto);
    // Delete
    //  int delete(int pid);
    //  int delete(String pname);
    
}