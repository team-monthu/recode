package com.monthu.recode.domain.feed.infra.database.converter;

import com.monthu.recode.domain.feed.domain.Contents;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class ContentsConverter implements AttributeConverter<Contents, String> {

  @Override
  public String convertToDatabaseColumn(Contents contents) {
    return contents == null ? null : contents.getMarkdown();
  }

  @Override
  public Contents convertToEntityAttribute(String markdown) {
    return markdown == null ? null : new Contents(markdown);
  }

}
