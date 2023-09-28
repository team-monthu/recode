package com.monthu.recode.global.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

class MarkdownUtilTest {

  @MockBean MarkdownUtil markdownUtil;

  @Test
  void parseMarkdownToHtml(){
    String markdown = "I am **Iron** man";
    String html = "<p>I am <strong>Iron</strong> man</p>";
    BDDMockito.given(markdownUtil.renderMarkdownToHtml(markdown)).willReturn(html);
  }

}