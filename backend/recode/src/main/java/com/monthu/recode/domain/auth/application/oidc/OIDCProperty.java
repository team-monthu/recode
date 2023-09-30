package com.monthu.recode.domain.auth.application.oidc;

import lombok.Getter;

@Getter
public class OIDCProperty {

  private String issuer;
  private String audience;

  public OIDCProperty(String issuer, String audience) {
    this.issuer = issuer;
    this.audience = audience;
  }
}
