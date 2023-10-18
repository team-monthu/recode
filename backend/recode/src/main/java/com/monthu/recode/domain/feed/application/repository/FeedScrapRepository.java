package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedScrap;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;

public interface FeedScrapRepository {

    FeedScrap save(FeedScrap feedScrap);

    Optional<FeedScrap> findByMemberAndFeed(Member member, Feed feed);

    void delete(FeedScrap feedScrap);
}
