package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.global.util.MarkdownUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedUseCase {

  private final FeedRepository feedRepository;
  private final MarkdownUtil markdownUtil;

  public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto) {
    String htmlContent = markdownUtil.renderMarkdownToHtml(writeFeedReqDto.getContent());

    Feed feed = feedRepository.save(writeFeedReqDto.of(writeFeedReqDto, htmlContent));

    return WriteFeedResDto.builder()
        .feedId(feed.getId())
        .build();
  }
}
