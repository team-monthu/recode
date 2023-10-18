package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedListResDto {

    private Long id;
    private String title;
    private String contents;
    private Integer likeCount;
    private Integer commentCount;
    private MemberResDto writer;
    private Integer viewCount;
    private CommentResDto adoptedComment;
    private List<String> stacks;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public FeedListResDto(Feed feed, MemberResDto writer, CommentResDto adoptedComment,
            Integer likeCount, Integer commentCount) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getContents().getHtml();
        this.writer = writer;
        this.viewCount = feed.getViewCount();
        this.adoptedComment = adoptedComment;
        this.createdAt = feed.getCreatedAt();
        this.modifiedAt = feed.getModifiedAt();
        this.stacks = feed.getFeedStacks().stream()
                .map(feedStack -> feedStack.getTechStack().getImageUrl())
                .collect(Collectors.toList());
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

}
