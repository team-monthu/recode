package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;

public interface FeedUseCase {
  WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto);
}
