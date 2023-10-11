package com.monthu.recode.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDto {

  private Boolean isNewMember;
  private String accessToken;
  private String refreshToken;

  @Builder
  public LoginResponseDto(Boolean isNewMember, String accessToken, String refreshToken) {
    this.isNewMember = isNewMember;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public static LoginResponseDto loggedIn(String accessToken, String refreshToken) {
    return LoginResponseDto.builder()
                           .isNewMember(false)
                           .accessToken(accessToken)
                           .refreshToken(refreshToken)
                           .build();
  }
}
