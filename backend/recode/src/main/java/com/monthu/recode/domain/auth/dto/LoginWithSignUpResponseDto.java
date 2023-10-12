package com.monthu.recode.domain.auth.dto;

import com.monthu.recode.domain.auth.application.oidc.OIDCMember;
import lombok.Getter;

@Getter
public class LoginWithSignUpResponseDto extends LoginResponseDto {

  private String email;
  private String profileImageUrl;


  public LoginWithSignUpResponseDto(OIDCMember oidcMember) {
    super(true, null, null);
    this.email = oidcMember.getEmail();
    this.profileImageUrl = oidcMember.getProfileImageUrl();
  }
}
