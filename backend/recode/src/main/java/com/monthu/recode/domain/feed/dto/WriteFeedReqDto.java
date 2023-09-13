package com.monthu.recode.domain.feed.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class WriteFeedReqDto {

  private String title;
  private String content;
  private List<Long> stacks;


}
