package com.monthu.recode.domain.member.infra.database;

import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.domain.member.dto.MemberInfoProjectionDto;
import com.monthu.recode.domain.member.infra.database.jpa.MemberJpaRepository;
import com.monthu.recode.domain.member.infra.database.query.MemberQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findByOauthProviderAndOauthId(OauthProvider oauthProvider,
            String oauthId) {
        return memberJpaRepository.findByOauthProviderAndOauthId(oauthProvider, oauthId);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public boolean existsByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId) {
        return memberJpaRepository.existsByOauthProviderAndOauthId(oauthProvider, oauthId);
    }

    @Override
    public MemberInfoProjectionDto findMemberDetailsById(Long memberId) {
        return memberQueryRepository.findMemberDetailsById(memberId);
    }
}
