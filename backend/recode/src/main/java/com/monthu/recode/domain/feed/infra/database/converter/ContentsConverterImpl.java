package com.monthu.recode.domain.feed.infra.database.converter;


//import com.monthu.recode.domain.feed.application.repository.AttributeConverter;

import com.monthu.recode.domain.feed.domain.Contents;
import com.monthu.recode.global.util.MarkdownUtil;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class ContentsConverterImpl implements AttributeConverter<Contents, String> {

  private final MarkdownUtil markdownUtil;

  @Override
  public String convertToDatabaseColumn(Contents contents) {
    return contents == null ? null : contents.getMarkdown();
  }

  @Override
  public Contents convertToEntityAttribute(String markdown) {
    String html = markdownUtil.renderMarkdownToHtml(markdown);
    return markdown == null ? null : new Contents(markdown, html);
  }

}
