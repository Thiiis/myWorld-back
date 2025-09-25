package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;


@Mapper
public interface MemberDao {
     void insert(Member member);
     Member selectByMid(Long mid);
     Member selectByAccount(@Param("account") String account);
     int update(Member member);
     int deleteByMid(Long mid);
     int deleteByAccount(String account);

    // MemberMapper.xml의 id="existsByAccount" 쿼리를 호출합니다.
     int existsByAccount(String account);
    
}