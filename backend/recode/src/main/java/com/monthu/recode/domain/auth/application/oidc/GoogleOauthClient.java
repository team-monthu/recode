package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.auth.dto.OIDCPublicKeyList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "GoogleOauthClient", url = "https://www.googleapis.com/oauth2/v3/certs")
public interface GoogleOauthClient {

    @Cacheable("googlePublicKeys")
    @GetMapping
    OIDCPublicKeyList getGoogleOIDCOpenKeys();
}
