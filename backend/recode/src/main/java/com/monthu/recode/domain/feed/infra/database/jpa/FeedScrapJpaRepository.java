package com.monthu.recode.domain.feed.infra.database.jpa;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedScrap;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedScrapJpaRepository extends JpaRepository<FeedScrap, Long> {

    Optional<FeedScrap> findByMemberAndFeed(Member member, Feed feed);

}
