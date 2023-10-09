package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.FeedCreateUseCase;
import com.monthu.recode.domain.feed.application.FeedDeleteUseCase;
import com.monthu.recode.domain.feed.application.FeedModifyUseCase;
import com.monthu.recode.domain.feed.application.FeedSearchUseCase;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.FeedListResDto;
import com.monthu.recode.domain.feed.dto.ModifyFeedDto;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.global.dto.HttpResponse;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedCreateUseCase feedCreateUseCase;
    private final FeedSearchUseCase feedSearchUseCase;
    private final FeedModifyUseCase feedModifyUseCase;
    private final FeedDeleteUseCase feedDeleteUseCase;

    @PostMapping
    public ResponseEntity<?> writeFeed(@RequestBody @Validated WriteFeedReqDto writeFeedReqDto) {
        return HttpResponse.okWithData(HttpStatus.OK,
                "피드가 작성되었습니다.",
                feedCreateUseCase.writeFeed(writeFeedReqDto));
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<?> findFeedDetails(@PathVariable Long feedId,
            @RequestParam(defaultValue = "true") Boolean updateViewCount) {
        return HttpResponse.okWithData(HttpStatus.OK, "피드 상세 정보를 반환합니다.",
                feedSearchUseCase.searchFeedDetailsById(feedId, updateViewCount));
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<?> findFeedDetailsWithMarkdown(@PathVariable Long feedId){
        return HttpResponse.okWithData(HttpStatus.OK, "피드 상세 조회를 반환합니다.",
                feedSearchUseCase.searchFeedDetailsWithMarkdown(feedId));
    }

    @GetMapping("/main")
    public ResponseEntity<?> findMainFeeds(Pageable pageable) {
        Page<Feed> feeds = feedSearchUseCase.searchMainFeeds(pageable);
        return HttpResponse.okWithData(HttpStatus.OK, "메인 피드 목록을 반환합니다.",
                new PageImpl<>(
                        feeds.stream().map(FeedListResDto::from).collect(Collectors.toList()),
                        pageable, feeds.getTotalElements()));
    }

    @PatchMapping
    public ResponseEntity<?> updateFeed(@RequestBody ModifyFeedDto modifyFeedDto) {
        feedModifyUseCase.modifyFeed(modifyFeedDto);
        return HttpResponse.ok(HttpStatus.OK, "피드 수정이 완료되었습니다.");
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long feedId){
        feedDeleteUseCase.deleteFeed(feedId);
        return HttpResponse.ok(HttpStatus.OK, "피드가 삭제되었습니다.");
    }

}
