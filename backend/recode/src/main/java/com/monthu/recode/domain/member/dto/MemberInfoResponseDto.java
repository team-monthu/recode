package com.monthu.recode.domain.member.dto;

import com.monthu.recode.domain.member.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {

    private String nickname;
    private String company;
    private String profileImageUrl;
    private String bio;
    private List<String> stackImageUrls;
    // TODO : tier
    private int feedCount;
    private int followerCount;
    private int followingCount;

    public MemberInfoResponseDto(MemberInfoProjectionDto projectionDto) {
        Member member = projectionDto.getMember();
        this.nickname = member.getNickname();
        this.company = member.getCompany();
        this.profileImageUrl = member.getProfileImageUrl();
        this.bio = member.getBio();
        this.stackImageUrls = member.getMemberStacks()
                                    .stream()
                                    .map(memberStack -> memberStack.getTechStack()
                                                                   .getImageUrl())
                                    .collect(Collectors.toList());
        this.feedCount = projectionDto.getFeedCount();
        this.followerCount = projectionDto.getFollowerCount();
        this.followingCount = projectionDto.getFollowingCount();
    }
}
