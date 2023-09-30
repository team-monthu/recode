package com.monthu.recode.domain.auth.controller;

import com.monthu.recode.domain.auth.application.LoginUseCase;
import com.monthu.recode.domain.auth.dto.LoginRequestDto;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class OauthController {

  private final LoginUseCase loginUseCase;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
    return HttpResponse.okWithData(HttpStatus.OK, "로그인 성공",
        loginUseCase.oauthLogin(loginRequestDto));
  }

}
