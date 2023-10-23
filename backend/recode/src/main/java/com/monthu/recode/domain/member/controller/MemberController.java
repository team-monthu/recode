package com.monthu.recode.domain.member.controller;

import com.monthu.recode.domain.member.application.MemberSearchUseCase;
import com.monthu.recode.global.config.webmvc.AuthMember;
import com.monthu.recode.global.config.webmvc.JwtLoginMember;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSearchUseCase memberSearchUseCase;

    @GetMapping("")
    public ResponseEntity<?> searchMyInfo(@JwtLoginMember AuthMember authMember) {
        return HttpResponse.okWithData(HttpStatus.OK, "내 정보 조회 성공",
                memberSearchUseCase.searchMyInfo(authMember));
    }
}
