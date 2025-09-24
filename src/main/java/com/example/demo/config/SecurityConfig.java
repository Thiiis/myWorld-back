package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF(Cross-Site Request Forgery) 보호 비활성화 (stateless API에서는 보통 비활성화)
            .csrf(csrf -> csrf.disable())

            // 2. 세션 관리를 STATELESS로 설정 (JWT 사용 시 세션을 사용하지 않음)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // 3. HTTP 요청에 대한 접근 권한 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/jukebox/**").permitAll()
                .anyRequest().permitAll()
                //.requestMatchers("/members/signup", "/members/login").permitAll()
                // 나머지 모든 요청은 인증 필요
                //.anyRequest().authenticated()
            );

        return http.build();
    }
}
