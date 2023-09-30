package com.monthu.recode.domain.auth.application.oidc;

import com.monthu.recode.domain.auth.application.jwt.JwtValidator;
import com.monthu.recode.domain.auth.dto.OIDCPublicKey;
import com.monthu.recode.global.exception.AuthenticationFailException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public abstract class IDTokenValidator {

  private final JwtValidator jwtValidator;
  private final OIDCProperty oidcProperty;

  public IDTokenValidator(JwtValidator jwtValidator, OIDCProperty oidcProperty) {
    this.jwtValidator = jwtValidator;
    this.oidcProperty = oidcProperty;
  }

  public OIDCMember getOIDCMemberByIDToken(String idToken) {
    jwtValidator.validateIDTokenByIssAndAud(idToken, oidcProperty.getIssuer(),
        oidcProperty.getAudience());
    String kidFromIdToken = jwtValidator.getKidFromHeader(idToken);

    OIDCPublicKey oidcPublicKey = getMatchedPublicKeyByKID(kidFromIdToken);
    Map<String, Object> payload = jwtValidator.getPayloadFromSignedToken(idToken,
        oidcPublicKey.getN(), oidcPublicKey.getE());

    return extractUserInfo(payload);
  }

  private OIDCPublicKey getMatchedPublicKeyByKID(String kid) {
    List<OIDCPublicKey> oidcPublicKeys = getOIDCPublicKeys();
    return oidcPublicKeys.stream()
                         .filter(o -> o.getKid()
                                       .equals(kid))
                         .findFirst()
                         .orElseThrow(() -> new AuthenticationFailException("일치하는 공개키가 없습니다."));
  }

  abstract List<OIDCPublicKey> getOIDCPublicKeys();

  abstract OIDCMember extractUserInfo(Map<String, Object> payload);
}
