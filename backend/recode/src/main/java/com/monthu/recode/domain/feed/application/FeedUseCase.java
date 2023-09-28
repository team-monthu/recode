package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.global.util.MarkdownUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class FeedUseCase {

  private final FeedRepository feedRepository;
  private final MarkdownUtil markdownUtil;

  public FeedUseCase(FeedRepository feedRepository, MarkdownUtil markdownUtil){
    this.feedRepository = feedRepository;
    this.markdownUtil = markdownUtil;
  }

  public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto) {
    String htmlContent = markdownUtil.renderMarkdownToHtml(writeFeedReqDto.getContent());

    Feed feed = feedRepository.save(writeFeedReqDto.from(htmlContent));

    return WriteFeedResDto.builder()
        .feedId(feed.getId())
        .build();
  }
}
