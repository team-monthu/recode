package com.monthu.recode.domain.feed.exception;

import com.monthu.recode.global.exception.ResourceNotFoundException;

public class FeedNotFoundException extends ResourceNotFoundException {

  private static final String message = "해당 피드를 찾을 수 없습니다.";

  public FeedNotFoundException() {
    super(message);
  }
}
