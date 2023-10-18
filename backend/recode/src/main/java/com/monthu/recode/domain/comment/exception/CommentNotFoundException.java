package com.monthu.recode.domain.comment.exception;

import com.monthu.recode.global.exception.ResourceNotFoundException;

public class CommentNotFoundException extends ResourceNotFoundException {

    private final static String message = "댓글을 찾을 수 없습니다.";

    public CommentNotFoundException() {
        super(message);
    }
}
