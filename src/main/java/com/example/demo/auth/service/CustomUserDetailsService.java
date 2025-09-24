package com.example.demo.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Member member = memberDao.selectByAccount(account);
        if (member == null) {
            throw new UsernameNotFoundException(account + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
        // Member 객체는 UserDetails를 구현했으므로 바로 리턴 가능
        return member;
    }
}