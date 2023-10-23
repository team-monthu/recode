package com.monthu.recode.global.config.security.filter;

import com.monthu.recode.domain.auth.application.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String HEADER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractToken(request);

        if (StringUtils.hasText(accessToken) && jwtProvider.validateAccessToken(accessToken)) {
            Claims claims = jwtProvider.getClaims(accessToken);
            String userId = claims.getSubject();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userId, "",
                    new ArrayList<>());

            // 요청 - 응답 스코프까지 살아있는 시큐리티 세션에 저장
            SecurityContextHolder.getContext()
                                 .setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(HEADER_PREFIX.length());
        }
        return null;
    }
}
