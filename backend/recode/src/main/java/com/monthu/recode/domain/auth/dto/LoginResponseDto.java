package com.monthu.recode.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDto {

    Boolean isNewUser;
    String accessToken;
    String refreshToken;

    @Builder
    public LoginResponseDto(Boolean isNewUser, String accessToken, String refreshToken) {
        this.isNewUser = isNewUser;
        this.accessToken = accessToken;
        this.refreshToken = accessToken;
    }

    public static LoginResponseDto isNew() {
        return LoginResponseDto.builder()
                               .isNewUser(true)
                               .accessToken(null)
                               .refreshToken(null)
                               .build();
    }

    public static LoginResponseDto logined(String accessToken, String refreshToken) {
        return LoginResponseDto.builder()
                               .isNewUser(false)
                               .accessToken(accessToken)
                               .refreshToken(refreshToken)
                               .build();
    }
}
