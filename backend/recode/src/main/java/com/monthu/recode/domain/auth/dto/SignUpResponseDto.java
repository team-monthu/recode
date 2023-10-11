package com.monthu.recode.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

  private String accessToken;
  private String refreshToken;
  private String nickname;
  private String profileImageUrl;

  @Builder
  public SignUpResponseDto(String accessToken, String refreshToken, String nickname,
      String profileImageUrl) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.nickname = nickname;
    this.profileImageUrl = profileImageUrl;
  }
}
