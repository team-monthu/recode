package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.comment.domain.Comment;
import lombok.Builder;

@Builder
public class CommentResDto {
    private String title;
    private String content;
    private Integer likeCount;
    private MemberResDto writer;

    public static CommentResDto of(Comment comment, MemberResDto writer){
        return CommentResDto.builder()
                .title(comment.getTitle())
                .content(comment.getContent())
                .writer(writer)
                // todo likeCount 추가
                .build();
    }


}
