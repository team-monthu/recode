package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Contents;

public interface ContentsConverter {

  public String convertToDatabaseColumn(Contents content);
  public Contents convertToEntityAttribute(String markdown);

}
