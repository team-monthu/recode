package com.monthu.recode.domain.feed.application;

import static org.junit.jupiter.api.Assertions.*;

import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import com.monthu.recode.global.util.MarkdownUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class FeedUseCaseTest {

  @MockBean
  FeedUseCase feedUseCase;
  @MockBean
  FeedRepositoryImpl feedRepository;
  @MockBean
  MarkdownUtil markdownUtil;








}