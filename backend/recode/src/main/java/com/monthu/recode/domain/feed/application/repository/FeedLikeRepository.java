package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedLike;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;

public interface FeedLikeRepository {

    FeedLike save(FeedLike feedLike);

    Optional<FeedLike> findByMemberAndFeed(Member member, Feed feed);

    void delete(FeedLike feedLike);

}
