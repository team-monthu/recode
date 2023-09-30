package com.monthu.recode.domain.feed.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Contents {

  private String html;
  private String markdown;


  public Contents(String markdown) {
    String html = renderMarkdownToHtml(markdown);
    this.markdown = markdown;
    this.html = html;
  }

  public String renderMarkdownToHtml(String markdown) {
    Parser parser = Parser.builder()
                          .build();
    Node document = parser.parse(markdown);
    HtmlRenderer renderer = HtmlRenderer.builder()
                                        .escapeHtml(true)
                                        .sanitizeUrls(true)
                                        .build();
    return renderer.render(document);
  }

}
