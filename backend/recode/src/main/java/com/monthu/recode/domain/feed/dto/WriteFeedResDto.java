package com.monthu.recode.domain.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WriteFeedResDto {

    private Long feedId;

    @Builder
    public WriteFeedResDto(Long feedId){
        this.feedId = feedId;
    }

}
