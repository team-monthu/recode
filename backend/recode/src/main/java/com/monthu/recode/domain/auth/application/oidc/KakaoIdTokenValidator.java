package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.auth.application.jwt.JwtValidator;
import com.monthu.recode.domain.auth.dto.OIDCPublicKey;
import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.global.exception.AuthenticationFailException;
import com.monthu.recode.global.property.KakaoOIDCProperty;
import feign.FeignException;
import java.util.List;
import java.util.Map;

public class KakaoIdTokenValidator extends IDTokenValidator {

    private final KakaoOauthClient kakaoOauthClient;
    private final String KAKAO_OAUTH_ID_KEY = "sub";
    private final String KAKAO_EMAIL_KEY = "email";
    private final String KAKAO_PROFILE_IMAGE_KEY = "picture";


    public KakaoIdTokenValidator(JwtValidator jwtValidator, KakaoOIDCProperty kakaoOIDCProperty,
            KakaoOauthClient kakaoOauthClient) {
        super(jwtValidator, kakaoOIDCProperty.toOIDCProperty());
        this.kakaoOauthClient = kakaoOauthClient;
    }

    @Override
    List<OIDCPublicKey> getOIDCPublicKeys() {
        try {
            return kakaoOauthClient.getKakaoOIDCOpenKeys()
                                   .getKeys();
        } catch (FeignException e) {
            throw new AuthenticationFailException("공개키 목록을 가져오는데 실패했습니다.");
        }
    }

    @Override
    OIDCMember extractUserInfo(Map<String, Object> payload) {
        checkPayload(payload);
        return buildOIDCMemberByCase(payload);
    }

    private OIDCMember buildOIDCMemberByCase(Map<String, Object> payload) {
        final String oAuthId = (String) payload.get(KAKAO_OAUTH_ID_KEY);
        final boolean CONTAINS_EMAIL = payload.containsKey(KAKAO_EMAIL_KEY);
        final boolean CONTAINS_PROFILE = payload.containsKey(KAKAO_PROFILE_IMAGE_KEY);

        if (CONTAINS_EMAIL && CONTAINS_PROFILE) {
            return OIDCMember.builder()
                             .oauthProvider(OauthProvider.KAKAO)
                             .oauthId(oAuthId)
                             .email((String) payload.get(KAKAO_EMAIL_KEY))
                             .profileImageUrl((String) payload.get(KAKAO_PROFILE_IMAGE_KEY))
                             .build();
        } else if (CONTAINS_EMAIL) {
            return OIDCMember.withEmail(OauthProvider.KAKAO, oAuthId,
                    (String) payload.get(KAKAO_EMAIL_KEY));
        } else if (CONTAINS_PROFILE) {
            return OIDCMember.withProfile(OauthProvider.KAKAO, oAuthId,
                    (String) payload.get(KAKAO_PROFILE_IMAGE_KEY));
        } else {
            return OIDCMember.base(OauthProvider.KAKAO, oAuthId);
        }
    }

    private void checkPayload(Map<String, Object> payload) {
        if (payload.get(KAKAO_OAUTH_ID_KEY) == null) {
            throw new AuthenticationFailException("잘못된 토큰입니다.");
        }
    }
}
