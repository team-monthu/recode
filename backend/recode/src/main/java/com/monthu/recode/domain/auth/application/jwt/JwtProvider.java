package com.monthu.recode.domain.auth.application.jwt;

import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.global.exception.AuthenticationFailException;
import com.monthu.recode.global.property.JwtProperty;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class JwtProvider {

  private final Key accessKey;
  private final Key refreshKey;
  private final Integer accessExpiredMinute;
  private final Integer refreshExpiredDay;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessEncodeByte = Base64Utils.encode(jwtProperty.getAccessKey()
                                                            .getBytes());
    byte[] refreshEncodeByte = Base64Utils.encode(jwtProperty.getRefreshKey()
                                                             .getBytes());

    this.accessExpiredMinute = jwtProperty.getAccessExpiredMinute();
    this.refreshExpiredDay = jwtProperty.getRefreshExpiredDay();
    this.accessKey = Keys.hmacShaKeyFor(accessEncodeByte);
    this.refreshKey = Keys.hmacShaKeyFor(refreshEncodeByte);
  }

  public String generateAccessToken(Member member) {
    Instant accessExpiredTime = Instant.now()
                                       .plus(accessExpiredMinute, ChronoUnit.MINUTES);
    return Jwts.builder()
               .setSubject(member.getId()
                                 .toString())
               .setExpiration(Date.from(accessExpiredTime))
               .signWith(accessKey)
               .compact();
  }

  public String generateRefreshToken() {
    Instant refreshExpiredTime = Instant.now()
                                        .plus(this.refreshExpiredDay, ChronoUnit.DAYS);
    return Jwts.builder()
               .setExpiration(Date.from(refreshExpiredTime))
               .signWith(refreshKey)
               .compact();
  }

  public boolean validateAccessToken(String accessToken) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(accessKey)
          .build()
          .parseClaimsJws(accessToken);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      throw new AuthenticationFailException("잘못된 서명입니다.");
    } catch (ExpiredJwtException e) {
      throw new AuthenticationFailException("만료된 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      throw new AuthenticationFailException("지원되지 않는 토큰입니다.");
    } catch (IllegalArgumentException e) {
      throw new AuthenticationFailException("잘못된 토큰입니다.");
    }
  }

  public void validateRefreshToken(String refreshToken) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(refreshKey)
          .build()
          .parseClaimsJws(refreshToken);
    } catch (Exception e) {
      throw new AuthenticationFailException("잘못된 토큰입니다.");
    }
  }
}
