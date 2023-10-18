package com.monthu.recode.domain.feed.infra.database.jpa;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedLike;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeJpaRepository extends JpaRepository<FeedLike, Long> {

    Optional<FeedLike> findByMemberAndFeed(Member member, Feed feed);

}
