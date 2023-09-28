package com.monthu.recode.domain.auth.application;

import com.monthu.recode.domain.auth.application.jwt.JwtProvider;
import com.monthu.recode.domain.auth.application.oidc.IDTokenValidatorHandler;
import com.monthu.recode.domain.auth.application.oidc.OIDCMember;
import com.monthu.recode.domain.auth.dto.LoginRequestDto;
import com.monthu.recode.domain.auth.dto.LoginResponseDto;
import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginUseCase {

    private final IDTokenValidatorHandler idTokenValidatorHandler;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public LoginResponseDto oauthLogin(LoginRequestDto loginRequestDto) {
        OIDCMember oidcMember = idTokenValidatorHandler.verifyIDToken(
                loginRequestDto.getOauthProvider(),
                loginRequestDto.getIdToken());

        Optional<Member> savedMember = memberRepository.findByOauthProviderAndOauthId(
                oidcMember.getOauthProvider(),
                oidcMember.getOauthId());

        if (savedMember.isEmpty()) {
            return LoginResponseDto.isNew();
        }

        Member member = savedMember.get();
        String accessToken = jwtProvider.generateAccessToken(member);
        String refreshToken =
                loginRequestDto.getIsLoginKeep() ? jwtProvider.generateRefreshToken() : null;
        member.setRefreshToken(refreshToken);
        return LoginResponseDto.logined(accessToken, refreshToken);
    }
}
