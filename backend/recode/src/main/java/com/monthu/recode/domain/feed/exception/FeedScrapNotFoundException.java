package com.monthu.recode.domain.feed.exception;

import com.monthu.recode.global.exception.ResourceNotFoundException;

public class FeedScrapNotFoundException extends ResourceNotFoundException {

    private static final String message = "스크랩을 찾을 수 없습니다.";

    public FeedScrapNotFoundException(){super(message);}
}
