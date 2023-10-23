package com.monthu.recode.global.config.webmvc;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080") // TODO : 도메인 추가
                .allowedOriginPatterns("POST", "GET", "DELETE", "PATCH", "OPTIONS")
                .allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(jwtLoginMemberArgumentResolver());
    }

    @Bean
    HandlerMethodArgumentResolver jwtLoginMemberArgumentResolver() {
        return new JwtLoginMemberArgumentResolver();
    }
}
