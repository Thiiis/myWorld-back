package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;


@Mapper
public interface MemberDao {
    public int insert(Member member);
    public Member selectByMid(Long mid);
    public Member selectByAccount(@Param("account") String account);
    public int update(Member member);
    public int deleteByMid(Long mid);
    public int deleteByAccount(String account);

    // MemberMapper.xml의 id="existsByAccount" 쿼리를 호출합니다.
    public int existsByAccount(String account);
    
}