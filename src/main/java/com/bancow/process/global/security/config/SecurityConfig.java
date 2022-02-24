package com.bancow.process.global.security.config;

import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.global.security.jwt.JwtAuthenticationFilter;
import com.bancow.process.global.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;
    private final FarmRepository farmRepository;

    //BCryptPasswordEncoder Bean 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // SpringSecurity를 사용할 때 RequestRejectedException을 처리하기 위해 적용
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    // SpringSecurity를 사용할 때 RequestRejectedException을 처리하기 위해 적용
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(defaultHttpFirewall());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable();
        http.headers().frameOptions().disable();
        // 세션을 사용하지 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() // 폼 로그인 형태 안씀
                .httpBasic().disable() // httpBasic의 방식 안씀
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), farmRepository))

                .authorizeRequests()

                .antMatchers("/api/sendSMS").permitAll() // /api/sendSMS 주소로 호출되는 api는 모두 허용
                .antMatchers("/api/login").permitAll() // /api/login 주소로 호출되는 api는 모두 허용
                .antMatchers("/api/test").permitAll()
                .antMatchers("/api/auth/token").permitAll()
                .anyRequest().authenticated(); // 위의 주소로 호출하는 경우 이외의 모든 호출시에 인증 필요
    }

}
