package com.monthu.recode.domain.member.exception;

import com.monthu.recode.global.exception.ResourceNotFoundException;

public class MemberNotFoundException extends ResourceNotFoundException {

    private final static String message = "멤버를 찾을 수 없습니다.";
    public MemberNotFoundException() {
        super(message);
    }

}
