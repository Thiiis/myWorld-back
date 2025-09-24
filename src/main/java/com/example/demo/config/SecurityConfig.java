package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.filter.JwtAuthenticationFilter;
import com.example.demo.auth.service.CustomUserDetailsService;
import com.example.demo.auth.service.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth
            // 회원가입/로그인은 누구나 접근 가능
            .requestMatchers("/auth/**").permitAll()

            // 미니홈피 조회(GET)는 누구나 가능
            .requestMatchers(HttpMethod.GET, "/api/homepages/**").permitAll()

            // 미니홈피에 무언가를 작성/수정/삭제(POST, PUT, DELETE)하는 행위는 인증 필요
            .requestMatchers(HttpMethod.POST, "/api/homepages/**").authenticated()
            .requestMatchers(HttpMethod.PUT, "/api/homepages/**").authenticated()
            .requestMatchers(HttpMethod.DELETE, "/api/homepages/**").authenticated()

            // 그 외 나머지 요청은 인증된 사용자만 가능
            .anyRequest().authenticated())
            // ** 직접 만든 JwtAuthenticationFilter를 필터 체인에 추가 **
            .addFilterBefore(new JwtAuthenticationFilter(jwtService, customUserDetailsService),
                    UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter 추가
        return http.build();
    }
}