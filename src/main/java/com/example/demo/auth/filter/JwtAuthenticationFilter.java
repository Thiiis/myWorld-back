package com.example.demo.auth.filter; // 패키지는 적절히 맞춰주세요

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.auth.service.CustomUserDetailsService;
import com.example.demo.auth.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String account = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            // ** 기존 JwtService의 유효성 검증 및 정보 추출 메소드 활용 **
            if (jwtService.validateJwt(jwt)) {
                Map<String, String> claims = jwtService.getClaims(jwt);
                // mid 대신 account를 subject로 사용했다면 claims.get("sub") 또는 claims.getSubject()
                // 여기서는 mid를 subject로 사용했으므로, DB 조회를 위해 mid 또는 account가 claim에 있어야 합니다.
                // createJWT 시 account를 claim에 추가하는 것을 권장합니다.
                // 임시로 subject를 account로 가정하겠습니다.
                 account = jwtService.getClaims(jwt).get("account"); // createJWT의 subject가 mid
            }
        }

        if (account != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // DB에서 사용자 정보 조회
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(account);
            
            // 인증 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            
            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}