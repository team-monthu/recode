package com.monthu.recode.domain.auth.application.jwt;

import com.monthu.recode.global.exception.AuthenticationFailException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

  private final String KID = "kid";
  private final String ALG = "RSA";

  public void validateIDTokenByIssAndAud(String idToken, String iss, String aud) {
    try {
      Jwts.parserBuilder()
          .requireAudience(aud)
          .requireIssuer(iss)
          .build()
          .parseClaimsJwt(parseHeaderAndClaims(idToken));
    } catch (ExpiredJwtException e) {
      throw new AuthenticationFailException("만료된 토큰입니다.");
    } catch (Exception e) {
      throw new AuthenticationFailException("유효하지 않은 토큰입니다.");
    }
  }

  public String getKidFromHeader(String idToken) {
    try {
      Header header = Jwts.parserBuilder()
                          .build()
                          .parseClaimsJwt(
                              parseHeaderAndClaims(idToken))
                          .getHeader();

      if (header.containsKey(KID)) {
        return (String) header.get(KID);
      }
      throw new AuthenticationFailException("헤더에 kid가 없습니다.");
    } catch (Exception e) {
      throw new AuthenticationFailException("유효하지 않은 토큰입니다.");
    }
  }

  public Map<String, Object> getPayloadFromSignedToken(String idToken, String modulus,
      String exponent) {
    try {
      Claims body = Jwts.parserBuilder()
                        .setSigningKey(getRSAPublicKey(modulus, exponent))
                        .build()
                        .parseClaimsJws(idToken)
                        .getBody();
      return new HashMap<>(body);
    } catch (ExpiredJwtException e) {
      throw new AuthenticationFailException("만료된 토큰입니다.");
    } catch (Exception e) {
      throw new AuthenticationFailException("유효하지 않은 토큰입니다.");
    }
  }

  private Key getRSAPublicKey(String modulus, String exponent)
      throws InvalidKeySpecException, NoSuchAlgorithmException {
    KeyFactory keyFactory = KeyFactory.getInstance(ALG);
    byte[] decodeN = Base64.getUrlDecoder()
                           .decode(modulus);
    byte[] decodeE = Base64.getUrlDecoder()
                           .decode(exponent);
    BigInteger n = new BigInteger(1, decodeN);
    BigInteger e = new BigInteger(1, decodeE);

    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
    return keyFactory.generatePublic(keySpec);
  }

  private String parseHeaderAndClaims(String idToken) {
    String[] splitToken = idToken.split("\\.");
    if (splitToken.length != 3) {
      throw new AuthenticationFailException("유효하지 않은 토큰입니다.");
    }
    return splitToken[0] + "." + splitToken[1] + ".";
  }

}
