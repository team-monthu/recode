package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.WriteFeedUseCase;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

  private final WriteFeedUseCase writeFeedUseCase;

  @PostMapping
  public ResponseEntity<?> writeFeed(@RequestBody WriteFeedReqDto writeFeedReqDto,
      Long memberId) {
    return HttpResponse.okWithData(HttpStatus.OK,
        "피드가 작성되었습니다.",
        writeFeedUseCase.writeFeed(writeFeedReqDto, memberId));
  }

}
