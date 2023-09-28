package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedCreateUseCase {

  private final FeedRepository feedRepository;

  public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto) {

    Feed feed = feedRepository.save(writeFeedReqDto.createFeed());
    return new WriteFeedResDto(feed.getId());

  }
}
