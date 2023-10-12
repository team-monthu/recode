package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.domain.techStack.application.repository.TechStackRepository;
import com.monthu.recode.domain.techStack.domain.TechStack;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedCreateUseCase {

  private final FeedRepository feedRepository;
  private final TechStackRepository techStackRepository;

  public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto) {
    List<TechStack> stacks = techStackRepository.findByIdIn(writeFeedReqDto.getStackIds());
    increaseTaggedCount(stacks);
    Feed feed = feedRepository.save(writeFeedReqDto.createFeedWithStacks(stacks));
    return new WriteFeedResDto(feed.getId());
  }

  private void increaseTaggedCount(List<TechStack> stacks) {
    stacks.forEach(TechStack::tagged);
  }
}
