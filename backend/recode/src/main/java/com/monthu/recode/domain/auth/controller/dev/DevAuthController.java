package com.monthu.recode.domain.auth.controller.dev;

import com.monthu.recode.domain.auth.application.dev.DevLoginHelper;
import com.monthu.recode.domain.auth.dto.LoginRequestDto;
import com.monthu.recode.domain.member.domain.OauthProvider;
import com.monthu.recode.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DevAuthController {

    private final Environment environment;
    private final DevLoginHelper loginHelper;

    @PostMapping("/api/dev/auth")
    private ResponseEntity<?> devLogin(@RequestParam String oauthId) {
        if (!isLocalProfile(environment.getActiveProfiles())) {
            return HttpResponse.fail(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }
        return HttpResponse.okWithData(HttpStatus.OK, "개발 로그인 성공",
                loginHelper.login(new LoginRequestDto(
                        OauthProvider.KAKAO, oauthId)));
    }

    private boolean isLocalProfile(String[] profiles) {
        for (String profile : profiles) {
            if (!profile.equals("local")) {
                return false;
            }
        }
        return true;
    }

}
