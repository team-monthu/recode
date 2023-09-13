package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface WriteFeedUseCase {
  WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto, Long MemberId);
}
