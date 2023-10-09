package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.FindFeedDetailsResDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedSearchUseCase {

    private final FeedRepositoryImpl feedRepository;


    public FindFeedDetailsResDto searchFeedDetailsById(Long feedId,
            ChangeCountConsumer<Feed> consumer) {
        Feed foundFeed = feedRepository.findById(feedId).map(
                feed -> {
                    updateViewCount(feed, Feed::increaseViewCount);
                    return feed;
                })
                .orElseThrow(FeedNotFoundException::new);

        return FindFeedDetailsResDto.builder()
                .feed(foundFeed)
                .build();
    }

    @Transactional
    public void updateViewCount(Feed feed, ChangeCountConsumer<Feed> consumer) {
        consumer.changeCount(feed);
        feedRepository.save(feed);
    }

}
