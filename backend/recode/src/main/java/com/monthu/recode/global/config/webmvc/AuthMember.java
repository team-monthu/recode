package com.monthu.recode.global.config.webmvc;

import lombok.Getter;

@Getter
public class AuthMember {

    private final Long id;

    public AuthMember(Long id) {
        this.id = id;
    }
}
