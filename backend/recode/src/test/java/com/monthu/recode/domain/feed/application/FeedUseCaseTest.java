package com.monthu.recode.domain.feed.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.domain.feed.infra.database.jpa.FeedJpaRepository;
import com.monthu.recode.global.util.MarkdownUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class FeedUseCaseTest {

  @MockBean
  FeedUseCase feedUseCase;
  @MockBean
  FeedJpaRepository feedRepository;
  @MockBean
  MarkdownUtil markdownUtil;

  @Test
  void postFeedTest() {
    String markdown = "I am **Iron man**";
    String html = "<p>I am <strong>Iron man</strong></p>";

    WriteFeedReqDto writeFeedReqDto = WriteFeedReqDto.builder()
        .title("피드 업로드 테스트")
        .content(markdown)
        .writerId(1L)
        .stacks(List.of(1L, 2L))
        .build();

    Feed feed = new Feed();

    when(feedUseCase.writeFeed(writeFeedReqDto)).thenReturn(WriteFeedResDto.builder()
        .feedId(1L)
        .build());
    when(feedRepository.save(any(Feed.class))).thenReturn(feed);

    Long savedFeedId = feedUseCase.writeFeed(writeFeedReqDto).getFeedId();

    assertThat(feedRepository.findById(savedFeedId).get().getContents()).isEqualTo(markdown);

  }

}