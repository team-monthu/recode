package com.monthu.recode.domain.member.infra.database.jpa;

import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.domain.OauthProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);

  boolean existsByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);
}
