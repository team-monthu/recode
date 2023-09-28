package com.monthu.recode.domain.feed.infra.database.converter;


import com.monthu.recode.domain.feed.application.repository.ContentsConverter;
import com.monthu.recode.domain.feed.domain.Contents;
import com.monthu.recode.global.util.MarkdownUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentsConverterImpl implements ContentsConverter {

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
