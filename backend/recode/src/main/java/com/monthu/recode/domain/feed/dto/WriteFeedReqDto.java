package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.techStack.domain.TechStack;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Builder
public class WriteFeedReqDto {

  @NotBlank
  @Size(max = 20)
  private String title;

  @NotBlank
  private String markdown;

  @NotNull
  private Long writerId;

  @Size(max = 10)
  private List<@Min(1) Long> stackIds;

  public Feed createFeedWithStacks(List<TechStack> stacks) {
    return Feed.builder()
               .markdown(markdown)
               .writerId(writerId)
               .title(title)
               .stacks(stacks)
               .build();
  }

}
