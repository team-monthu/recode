package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.dto.ModifyFeedDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedModifyUseCase {

    private final FeedRepositoryImpl feedRepository;

    public void modifyFeed(ModifyFeedDto modifyFeedDto) {
        feedRepository.findById(modifyFeedDto.getId()).map(
                        feed -> {
                            feed.updateFeed(modifyFeedDto.getTitle(), modifyFeedDto.getMarkdown(),
                                    modifyFeedDto.getStacks());
                            feedRepository.save(feed);
                            return feed;
                        }
                )
                .orElseThrow(FeedNotFoundException::new);
    }
}
