package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.global.exception.AuthenticationFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IDTokenValidatorHandler {

  @Qualifier("kakaoIDTokenValidator")
  private final IDTokenValidator kakaoIDTokenValidator;

  public OIDCMember getOidcMemberByProviderAndIDToken(OauthProvider oauthProvider,
      String idToken) {
    switch (oauthProvider) {
      case KAKAO:
        return kakaoIDTokenValidator.getOIDCMemberByIDToken(idToken);
      case GOOGLE:
        System.out.println("GOOGLE 구현 중...");
      default:
        throw new AuthenticationFailException("해당 Provider를 제공하지 않습니다.");
    }

  }
}
