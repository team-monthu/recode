package com.monthu.recode.domain.feed.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Contents {

  private String html;
  private String markdown;

  public Contents(String markdown, String html){
    this.markdown = markdown;
    this.html = html;
  }

}
