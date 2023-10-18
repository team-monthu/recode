package com.monthu.recode.domain.feed.exception;

import com.monthu.recode.global.exception.ResourceNotFoundException;

public class FeedLikeNotFoundException extends ResourceNotFoundException {

    private static final String message = "좋아요를 찾을 수 없습니다.";

    public FeedLikeNotFoundException(){super(message);}

}
