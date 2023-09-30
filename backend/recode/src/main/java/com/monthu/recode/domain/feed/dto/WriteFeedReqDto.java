package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import java.util.List;
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
  @NotNull
  private String markdown;

  @NotNull
  private Long writerId;

  private List<Long> stacks;

  public Feed createFeed() {
    return Feed.builder()
               .markdown(markdown)
               .writerId(writerId)
               .title(title)
               .ids(stacks)
               .build();
  }

}
