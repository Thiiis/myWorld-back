package com.example.demo.auth.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.profile.dto.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Member implements UserDetails{
    private long mid;
    private Date cretedAt;
    private Date updatedAt;

    private String account;
    private String email;
    private String pwd;

    private Profile profile; //1대1관계

    public Member(String account,String email,String pwd){
        this.account = account;
        this.email = email;
        this.pwd = pwd;
    }
    
    @NotBlank
    @Email
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    // ===== UserDetails 인터페이스 메소드 구현 =====

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 현재는 단일 권한 "USER"를 부여하지만, DB에 권한 정보를 저장하고 가져오도록 확장할 수 있습니다.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.pwd; // 비밀번호 반환
    }

    @Override
    public String getUsername() {
        return this.account; // 로그인 ID로 사용할 계정 반환
    }

    // 계정이 만료되지 않았는지 리턴 (true: 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않은지 리턴 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴 (true: 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
