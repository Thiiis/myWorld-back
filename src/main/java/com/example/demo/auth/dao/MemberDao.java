package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;


@Mapper
public interface MemberDao {
     void insert(Member member);
     Member selectByAccount(String account);
     Member selectByMid(Long mid);
     void update(@Param("mid") Long mid, @Param("email") String email, @Param("pwd") String pwd);
     int deleteByMid(Long mid);

     int countByAccount(String account);
    
}