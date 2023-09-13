package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService implements WriteFeedUseCase {

  private final FeedRepository feedRepository;

  @Override
  public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto, Long memberId) {

    Optional<Feed> feed = feedRepository.save(Feed.builder()
        .title(writeFeedReqDto.getTitle())
        .content(writeFeedReqDto.getContent())
        .writerId(memberId)
        .viewCount(0)
        .build());

    WriteFeedResDto writeFeedResDto = WriteFeedResDto.builder()
        .feedId(feed.orElseThrow(RuntimeException::new).getId())
        .build();

    return writeFeedResDto;
  }
}
