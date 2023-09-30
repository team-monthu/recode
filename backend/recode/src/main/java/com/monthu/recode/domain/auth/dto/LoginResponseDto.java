package com.monthu.recode.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDto {

  Boolean isNewMember;
  String accessToken;
  String refreshToken;

  @Builder
  public LoginResponseDto(Boolean isNewMember, String accessToken, String refreshToken) {
    this.isNewMember = isNewMember;
    this.accessToken = accessToken;
    this.refreshToken = accessToken;
  }

  public static LoginResponseDto newMember() {
    return LoginResponseDto.builder()
                           .isNewMember(true)
                           .accessToken(null)
                           .refreshToken(null)
                           .build();
  }

  public static LoginResponseDto loggedInMember(String accessToken, String refreshToken) {
    return LoginResponseDto.builder()
                           .isNewMember(false)
                           .accessToken(accessToken)
                           .refreshToken(refreshToken)
                           .build();
  }
}
