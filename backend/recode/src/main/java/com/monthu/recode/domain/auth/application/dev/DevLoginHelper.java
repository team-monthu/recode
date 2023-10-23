package com.monthu.recode.domain.auth.application.dev;

import com.monthu.recode.domain.auth.application.jwt.JwtProvider;
import com.monthu.recode.domain.auth.dto.LoginRequestDto;
import com.monthu.recode.domain.auth.dto.LoginResponseDto;
import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DevLoginHelper {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByOauthProviderAndOauthId(
                                                loginRequestDto.getOauthProvider(),
                                                loginRequestDto.getIdToken())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                "개발 계정을 찾을 수 없습니다."));
        String accessToken = jwtProvider.generateAccessToken(member);
        String refreshToken = jwtProvider.generateRefreshToken();
        member.setRefreshToken(refreshToken);
        return LoginResponseDto.loggedIn(accessToken, refreshToken);
    }
}
