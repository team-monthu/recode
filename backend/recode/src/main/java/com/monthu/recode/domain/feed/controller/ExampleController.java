package com.monthu.recode.domain.feed.controller;

import com.monthu.recode.domain.feed.application.ExampleSearchUseCase;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleSearchUseCase exampleSearchUseCase;

    @GetMapping()
    public ResponseEntity<?> searchExampleById() {
        return HttpResponse.okWithData(HttpStatus.OK, "데이터 조회 성공",
                exampleSearchUseCase.searchExampleById(1L));
    }
}
