package com.monthu.recode.domain.feed.dto;

import lombok.Builder;

@Builder
public class MemberResDto {

    private Long memberId;
    private String nickname;
    private Integer rating;
    private String profileImageUrl;

}
