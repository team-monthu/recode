package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.Feed.TechStack;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindFeedDetailsResDto {

    private Long id;
    private String title;
    private String contents;
    private Long writerId;
    private Integer viewCount;
    private Long adoptedCommentId;
    private List<Long> stacks;

    @Builder
    public FindFeedDetailsResDto(Feed feed){
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getContents().getHtml();
        this.writerId = feed.getWriterId();
        this.viewCount = feed.getViewCount();
        this.adoptedCommentId = feed.getAdoptedCommentId();
        this.stacks = feed.getStacks().stream()
                .map(TechStack::getStackId)
                .collect(Collectors.toList());
    }

}
