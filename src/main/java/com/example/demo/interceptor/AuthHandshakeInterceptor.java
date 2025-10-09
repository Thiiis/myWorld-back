package com.example.demo.interceptor;

import com.example.demo.auth.service.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;

@Override
public boolean beforeHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Map<String, Object> attributes) throws Exception {

    String token = null;

    // 1) 헤더에서 Bearer 토큰 찾기
    var headers = request.getHeaders().get("Authorization");
    if (headers != null && !headers.isEmpty() && headers.get(0).startsWith("Bearer ")) {
        token = headers.get(0).substring(7);
    }

    // 2) 헤더에 없으면 쿼리스트링에서 jwt 찾기
    if (token == null && request instanceof ServletServerHttpRequest servletRequest) {
        token = servletRequest.getServletRequest().getParameter("jwt");
    }

    if (token != null && jwtService.validateJwt(token)) {
        var claims = jwtService.getClaims(token);
        attributes.put("loginMid", Long.parseLong(claims.get("mid")));
        attributes.put("loginAccount", claims.get("account"));
        return true;
    }

    response.setStatusCode(HttpStatus.FORBIDDEN);
    return false;  // 인증 실패 시 연결 거부
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) { }
}