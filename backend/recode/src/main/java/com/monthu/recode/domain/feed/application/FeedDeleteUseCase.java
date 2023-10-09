package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedDeleteUseCase {

    private final FeedRepositoryImpl feedRepository;

    public void deleteFeed(Long feedId) {
        feedRepository.findById(feedId).map(
                feed -> {
                    feed.deleteFeed();
                    feedRepository.save(feed);
                    return feed;
                }
        ).orElseThrow(FeedNotFoundException::new);
    }

}
