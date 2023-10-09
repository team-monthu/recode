package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.FeedListResDto;
import com.monthu.recode.domain.feed.dto.FindFeedDetailsResDto;
import com.monthu.recode.domain.feed.dto.FindFeedDetailsWithMarkdownResDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedSearchUseCase {

    private final FeedRepositoryImpl feedRepository;


    public FindFeedDetailsResDto searchFeedDetailsById(Long feedId, Boolean updateViewCount) {
        Feed foundFeed = feedRepository.findById(feedId).map(
                feed -> {
                    if(updateViewCount) updateViewCount(feed, Feed::increaseViewCount);
                    return feed;
                })
                .orElseThrow(FeedNotFoundException::new);

        return FindFeedDetailsResDto.builder()
                .feed(foundFeed)
                .build();
    }

    public FindFeedDetailsWithMarkdownResDto searchFeedDetailsWithMarkdown(Long feedId) {
        Feed foundFeed = feedRepository.findById(feedId).orElseThrow(FeedNotFoundException::new);
        return FindFeedDetailsWithMarkdownResDto.from(foundFeed);
    }

    @Transactional
    public void updateViewCount(Feed feed, ChangeCountConsumer<Feed> consumer) {
        consumer.changeCount(feed);
        feedRepository.save(feed);
    }

    public Page<Feed> searchMainFeeds(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

}
