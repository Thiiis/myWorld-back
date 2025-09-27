package com.example.demo.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileUpdateRequest;

@Mapper
 public interface ProfileDao {
     void insert(Profile profile); //Create

     // Read
     int countByNickname(String nickname);
     Profile selectByPid(Long pid);
    //  Profile selectByMid(Long mid);
    //  Profile selectByNickname(String nickname);
    // Update

    // Delete
    //  int delete(int pid);
    //  int delete(String pname);
    
}