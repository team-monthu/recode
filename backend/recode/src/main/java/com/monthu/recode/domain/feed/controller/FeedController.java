package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.FeedCreateUseCase;
import com.monthu.recode.domain.feed.application.FeedSearchUseCase;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedCreateUseCase feedCreateUseCase;
    private final FeedSearchUseCase feedSearchUseCase;

    @PostMapping
    public ResponseEntity<?> writeFeed(@RequestBody @Validated WriteFeedReqDto writeFeedReqDto) {
        return HttpResponse.okWithData(HttpStatus.OK,
                "피드가 작성되었습니다.",
                feedCreateUseCase.writeFeed(writeFeedReqDto));
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<?> findFeedDetails(@PathVariable Long feedId) {
        return HttpResponse.okWithData(HttpStatus.OK, "피드 상세 정보를 반환합니다.",
                feedSearchUseCase.searchFeedDetailsById(feedId));
    }

}
