package com.bancow.process.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.global.security.auth.PrincipalDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증이 필요한 api 요청이 오면 무조건 BasicAuthenticationFilter를 탐.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private FarmRepository farmRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, FarmRepository farmRepository) {
        super(authenticationManager);
        this.farmRepository = farmRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 클라이언트가 요청한 헤더에 Authorization이 있는지 검증/ 없으면 다시 필터 타도록
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("header : "+header);

        // JWT 토큰을 검증해서 정상적인 사용자인지 확인
        // 헤더에 키가 Authorization이면 값에 Bearer 뒤에 " "를 없앰(토큰 값만 추출하기 위해)
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        // 토큰을 서명하고 검증해서 통과하면 FarmName을 가져옴
        String userName = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("phoneNumber").asString();

        // 서명이 정상적으로 됐다면
        if(userName != null) {
            Farm farmEntity = farmRepository.findByPhoneNumber(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("번호를 찾을 수 없습니다. "));

            // 시큐리티가 수행해주는 권한 처리를 위해 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(farmEntity);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
