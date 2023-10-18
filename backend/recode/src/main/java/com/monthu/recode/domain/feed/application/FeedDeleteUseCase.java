package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedDeleteUseCase {

    private final FeedRepositoryImpl feedRepository;

    // todo 삭제 시 taggedCount 내리기
    @Transactional
    public void deleteFeed(Long feedId) {
        feedRepository.findById(feedId).map(
                feed -> {
                    feed.deleteFeed();
                    return feed;
                }
        ).orElseThrow(FeedNotFoundException::new);
    }

}
