package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.FeedLikeCreateUseCase;
import com.monthu.recode.domain.feed.application.FeedLikeDeleteUseCase;
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
@RequestMapping("api/feed/like")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeCreateUseCase feedLikeCreateUseCase;
    private final FeedLikeDeleteUseCase feedLikeDeleteUseCase;

    @PostMapping("/{feedId}")
    public ResponseEntity<?> addLike(@PathVariable Long feedId){
        feedLikeCreateUseCase.createLike(feedId);
        return HttpResponse.ok(HttpStatus.OK, "좋아요를 눌렀습니다.");
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<?> deleteLike(@PathVariable Long feedId){
        feedLikeDeleteUseCase.deleteLike(feedId);
        return HttpResponse.ok(HttpStatus.OK, "좋아요를 삭제하였습니다.");
    }

}
