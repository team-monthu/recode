package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.comment.domain.Comment;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.Feed.TechStack;
import com.monthu.recode.domain.member.domain.Member;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindFeedDetailsResDto {

    private Long id;
    private String title;
    private String contents;
    private Integer likeCount;
    private Integer commentCount;
    private MemberResDto writer;
    private Integer viewCount;
    private CommentResDto adoptedComment;
    private List<Long> stacks;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public FindFeedDetailsResDto(Feed feed, Member writer, Comment comment){
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getContents().getHtml();
        this.writerId = feed.getWriterId();
        this.viewCount = feed.getViewCount();
        this.adoptedCommentId = feed.getAdoptedCommentId();
        this.createdAt = feed.getCreatedAt();
        this.modifiedAt = feed.getModifiedAt();
        this.stacks = feed.getStacks().stream()
                .map(TechStack::getStackId)
                .collect(Collectors.toList());
    }

}
