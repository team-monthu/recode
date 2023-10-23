package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.FeedCreateUseCase;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.global.config.webmvc.AuthMember;
import com.monthu.recode.global.config.webmvc.JwtLoginMember;
import com.monthu.recode.global.dto.HttpResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedCreateUseCase feedCreateUseCase;

    @PostMapping("")
    public ResponseEntity<?> writeFeed(@Valid @RequestBody WriteFeedReqDto writeFeedReqDto,
            @JwtLoginMember AuthMember authMember) {
        return HttpResponse.okWithData(HttpStatus.OK,
                "피드가 작성되었습니다.",
                feedCreateUseCase.writeFeed(writeFeedReqDto, authMember));
    }

}
