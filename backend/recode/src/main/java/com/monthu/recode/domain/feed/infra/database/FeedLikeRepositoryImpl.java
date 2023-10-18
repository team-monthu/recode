package com.monthu.recode.domain.feed.infra.database;

import com.monthu.recode.domain.feed.application.repository.FeedLikeRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedLike;
import com.monthu.recode.domain.feed.infra.database.jpa.FeedLikeJpaRepository;
import com.monthu.recode.domain.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeedLikeRepositoryImpl implements FeedLikeRepository {

    private final FeedLikeJpaRepository feedLikeJpaRepository;

    @Override
    public FeedLike save(FeedLike feedLike) {
        return feedLikeJpaRepository.save(feedLike);
    }

    @Override
    public Optional<FeedLike> findByMemberAndFeed(Member member, Feed feed) {
        return feedLikeJpaRepository.findByMemberAndFeed(member, feed);
    }

    @Override
    public void delete(FeedLike feedLike) {
        feedLikeJpaRepository.delete(feedLike);
    }
}
