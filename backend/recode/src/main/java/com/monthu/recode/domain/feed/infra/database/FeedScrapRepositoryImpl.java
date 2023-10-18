package com.monthu.recode.domain.feed.infra.database;

import com.monthu.recode.domain.feed.application.repository.FeedScrapRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedScrap;
import com.monthu.recode.domain.feed.infra.database.jpa.FeedScrapJpaRepository;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedScrapRepositoryImpl implements FeedScrapRepository {

    private final FeedScrapJpaRepository feedScrapJpaRepository;

    @Override
    public FeedScrap save(FeedScrap feedScrap) {
        return feedScrapJpaRepository.save(feedScrap);
    }

    @Override
    public Optional<FeedScrap> findByMemberAndFeed(Member member, Feed feed) {
        return feedScrapJpaRepository.findByMemberAndFeed(member, feed);
    }

    @Override
    public void delete(FeedScrap feedScrap) {
        feedScrapJpaRepository.delete(feedScrap);
    }
}
