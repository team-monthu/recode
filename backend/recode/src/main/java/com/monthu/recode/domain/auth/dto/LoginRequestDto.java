package com.monthu.recode.domain.auth.dto;

import com.monthu.recode.domain.member.domain.OauthProvider;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    private OauthProvider oauthProvider;
    private String idToken;
    private Boolean isLoginKeep;
}
