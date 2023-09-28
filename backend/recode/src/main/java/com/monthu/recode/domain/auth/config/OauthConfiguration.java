package com.monthu.recode.domain.auth.config;

import com.monthu.recode.domain.auth.application.jwt.JwtValidator;
import com.monthu.recode.domain.auth.application.oidc.IDTokenValidator;
import com.monthu.recode.domain.auth.application.oidc.KakaoIdTokenValidator;
import com.monthu.recode.domain.auth.application.oidc.KakaoOauthClient;
import com.monthu.recode.global.property.KakaoOIDCProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OauthConfiguration {

    private final KakaoOauthClient kaKaoOauthClient;
    private final JwtValidator jwtValidator;
    private final KakaoOIDCProperty kakaoOIDCProperty;

    @Bean("kakaoIDTokenValidator")
    public IDTokenValidator kakaoIDTokenValidation() {
        return new KakaoIdTokenValidator(jwtValidator, kakaoOIDCProperty, kaKaoOauthClient);
    }

    //TODO : 구글 VALIDATOR 구현

}
