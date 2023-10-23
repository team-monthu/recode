package com.monthu.recode.domain.member.dto;

import com.monthu.recode.domain.member.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberInfoProjectionDto {

    private Member member;
    // TODO : tier
    private int feedCount;
    private int followerCount;
    private int followingCount;

    @QueryProjection
    public MemberInfoProjectionDto(Member member, int feedCount, int followerCount,
            int followingCount) {
        this.member = member;
        this.feedCount = feedCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}
