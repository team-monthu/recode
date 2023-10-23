package com.monthu.recode.domain.member.application;

import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.dto.MemberInfoProjectionDto;
import com.monthu.recode.domain.member.dto.MemberInfoResponseDto;
import com.monthu.recode.global.config.webmvc.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSearchUseCase {

    private final MemberRepository memberRepository;

    public MemberInfoResponseDto searchMyInfo(AuthMember authMember) {
        MemberInfoProjectionDto projectionDto = memberRepository.findMemberDetailsById(
                authMember.getId());
        return new MemberInfoResponseDto(projectionDto);
    }

}
