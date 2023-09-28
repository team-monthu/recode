package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.member.domain.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OIDCMember {

    private OauthProvider oauthProvider;
    private String oauthId;
    private String email;
    private String profileImageUrl;

    @Builder
    public OIDCMember(OauthProvider oauthProvider, String oauthId, String email,
            String profileImageUrl) {
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public static OIDCMember withEmail(OauthProvider oauthProvider, String oauthId, String email) {
        return OIDCMember.builder()
                         .oauthProvider(oauthProvider)
                         .oauthId(oauthId)
                         .email(email)
                         .profileImageUrl(null)
                         .build();
    }

    public static OIDCMember withProfile(OauthProvider oauthProvider, String oauthId,
            String profileImageUrl) {
        return OIDCMember.builder()
                         .oauthProvider(oauthProvider)
                         .oauthId(oauthId)
                         .profileImageUrl(profileImageUrl)
                         .email(null)
                         .build();
    }

    public static OIDCMember base(OauthProvider oauthProvider, String oauthId) {
        return OIDCMember.builder()
                         .oauthProvider(oauthProvider)
                         .oauthId(oauthId)
                         .email(null)
                         .profileImageUrl(null)
                         .build();
    }
}
