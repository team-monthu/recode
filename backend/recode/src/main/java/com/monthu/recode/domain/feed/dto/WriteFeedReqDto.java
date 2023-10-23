package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.techStack.domain.TechStack;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WriteFeedReqDto {

    @NotBlank
    @Size(max = 20)
    private String title;

    @NotBlank
    private String markdown;

    @Size(max = 10)
    private List<@Min(1) Long> stackIds;

    @Builder
    public WriteFeedReqDto(String title, String markdown, List<@Min(1) Long> stackIds) {
        this.title = title;
        this.markdown = markdown;
        this.stackIds = stackIds;
    }

    public Feed createFeedWithStacksAndWriter(List<TechStack> stacks, Member writer) {
        return Feed.builder()
                   .markdown(markdown)
                   .title(title)
                   .writer(writer)
                   .stacks(stacks)
                   .build();
    }

}
