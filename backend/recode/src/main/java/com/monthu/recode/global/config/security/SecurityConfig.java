package com.monthu.recode.global.config.security;

import com.monthu.recode.domain.auth.application.jwt.JwtProvider;
import com.monthu.recode.global.config.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // rest api, crsf 사용 x
        http.csrf()
            .disable();

        // 기본 login 방식 사용 x
        http.formLogin()
            .disable();

        // 인증 실패시 customEntryPoint로 이동
        // never create an HttpSession
        http.exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/dev/auth", "/api/member", "/api/member/login")
            .permitAll()
            .anyRequest()
            .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtProvider);
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
