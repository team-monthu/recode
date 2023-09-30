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
}
