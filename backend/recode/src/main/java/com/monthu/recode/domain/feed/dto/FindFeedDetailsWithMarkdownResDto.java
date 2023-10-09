package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.Feed.TechStack;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public class FindFeedDetailsWithMarkdownResDto {

    private Long id;
    private String title;
    private String contents;
    private Long writerId;
    private Integer viewCount;
    private Long adoptedCommentId;
    private List<Long> stacks;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public static FindFeedDetailsWithMarkdownResDto from(Feed feed){
        return FindFeedDetailsWithMarkdownResDto.builder()
                .id(feed.getId())
                .contents(feed.getContents().getMarkdown())
                .adoptedCommentId(feed.getAdoptedCommentId())
                .title(feed.getTitle())
                .viewCount(feed.getViewCount())
                .writerId(feed.getWriterId())
                .createdAt(feed.getCreatedAt())
                .modifiedAt(feed.getModifiedAt())
                .stacks(feed.getStacks().stream().map(TechStack::getStackId)
                        .collect(Collectors.toList()))
                .build();
    }

}
