package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class WriteFeedReqDto {

  @NotBlank
  @Size(max = 20)
  private String title;

  @NotBlank
  @NotNull private String content;

  @NotNull
  private Long writerId;

  private List<Long> stacks;

  public Feed of (WriteFeedReqDto writeFeedReqDto, String html){
    return Feed.builder()
        .title(writeFeedReqDto.getTitle())
        .content(html)
        .ids(writeFeedReqDto.getStacks())
        .writerId(writeFeedReqDto.getWriterId())
        .build();
  }


}
