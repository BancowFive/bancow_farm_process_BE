package com.bancow.process.global.security.jwt;

public interface JwtProperties {
    String SECRET = "bancow"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 1800000; // 30분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
