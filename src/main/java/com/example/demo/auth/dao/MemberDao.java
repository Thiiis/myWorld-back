package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.UpdateRequest;


@Mapper
public interface MemberDao {
     void insert(Member member);
     Member selectByAccount(String account);
     Member selectByMid(Long mid);
     Member update(UpdateRequest dto);
     int deleteByMid(Long mid);

     int countByAccount(String account);
    
}