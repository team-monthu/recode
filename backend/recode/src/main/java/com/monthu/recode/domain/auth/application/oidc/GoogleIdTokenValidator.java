package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.auth.application.jwt.JwtValidator;
import com.monthu.recode.domain.auth.dto.OIDCPublicKey;
import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.global.exception.AuthenticationFailException;
import com.monthu.recode.global.property.GoogleOIDCProperty;
import feign.FeignException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GoogleIdTokenValidator extends IDTokenValidator {

    private final GoogleOauthClient googleOauthClient;
    private final String GOOGLE_OAUTH_ID_KEY = "sub";
    private final String GOOGLE_EMAIL_KEY = "email";
    private final String GOOGLE_PROFILE_IMAGE_KEY = "picture";

    public GoogleIdTokenValidator(JwtValidator jwtValidator, GoogleOIDCProperty googleOIDCProperty,
            GoogleOauthClient googleOauthClient) {
        super(jwtValidator, googleOIDCProperty.toOIDCProperty());
        this.googleOauthClient = googleOauthClient;
    }

    @Override
    List<OIDCPublicKey> getOIDCPublicKeys() {
        try {
            return googleOauthClient.getGoogleOIDCOpenKeys()
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
        final String oAuthId = (String) payload.get(GOOGLE_OAUTH_ID_KEY);
        final boolean CONTAINS_EMAIL = payload.containsKey(GOOGLE_EMAIL_KEY);
        final boolean CONTAINS_PROFILE = payload.containsKey(GOOGLE_PROFILE_IMAGE_KEY);

        String email = CONTAINS_EMAIL ? (String) payload.get(GOOGLE_EMAIL_KEY) : null;
        String profileImageUrl =
                CONTAINS_PROFILE ? (String) payload.get(GOOGLE_PROFILE_IMAGE_KEY) : null;

        return OIDCMember.builder()
                         .oauthProvider(OauthProvider.KAKAO)
                         .oauthId(oAuthId)
                         .email(email)
                         .profileImageUrl(profileImageUrl)
                         .build();
    }

    private void checkPayload(Map<String, Object> payload) {
        if (payload.get(GOOGLE_OAUTH_ID_KEY) == null) {
            throw new AuthenticationFailException("잘못된 토큰입니다.");
        }
    }
}
