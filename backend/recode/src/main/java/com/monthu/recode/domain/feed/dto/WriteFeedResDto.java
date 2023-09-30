package com.monthu.recode.domain.feed.dto;

import lombok.Getter;

@Getter
public class WriteFeedResDto {

  private Long feedId;

  public WriteFeedResDto(Long feedId) {
    this.feedId = feedId;
  }

}
