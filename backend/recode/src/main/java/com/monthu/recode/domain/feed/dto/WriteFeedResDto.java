package com.monthu.recode.domain.feed.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteFeedResDto {

    private Long feedId;

    @Builder
    public WriteFeedResDto(Long feedId){
        this.feedId = feedId;
    }

}
