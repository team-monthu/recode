package com.monthu.recode.domain.member.application.repository;

import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.domain.member.dto.MemberInfoProjectionDto;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);

    Optional<Member> findById(Long id);

    boolean existsByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);

    MemberInfoProjectionDto findMemberDetailsById(Long memberId);
}
