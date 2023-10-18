package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.FeedScrapCreateUseCase;
import com.monthu.recode.domain.feed.application.FeedScrapDeleteUseCase;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feed/scrap")
@RequiredArgsConstructor
public class FeedScrapController {

    private final FeedScrapCreateUseCase feedScrapCreateUseCase;
    private final FeedScrapDeleteUseCase feedScrapDeleteUseCase;

    @PostMapping("/{feedId}")
    public ResponseEntity<?> addScrap(@PathVariable Long feedId){
        feedScrapCreateUseCase.createScrap(feedId);
        return HttpResponse.ok(HttpStatus.OK, "피드를 스크랩하였습니다.");
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity deleteScrap(@PathVariable Long feedId){
        feedScrapDeleteUseCase.deleteScrap(feedId);
        return HttpResponse.ok(HttpStatus.OK, "스크랩을 삭제하였습니다.");
    }

}
