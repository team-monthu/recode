package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.member.domain.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public class MemberResDto {

    private Long memberId;
    private String nickname;
    private Integer rating;
    private String profileImageUrl;

    public static MemberResDto from(Member member){
        // todo 티어 계산 로직 추가
        return MemberResDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .rating(member.getRating())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }

}
