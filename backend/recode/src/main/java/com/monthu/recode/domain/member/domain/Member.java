package com.monthu.recode.domain.member.domain;

import com.monthu.recode.global.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;
  
  @Enumerated(EnumType.STRING)
  @Column
  private OauthProvider oauthProvider;

  @Column
  private String oauthId;

  @Column
  private String nickname;

  @Column
  private String email;

  @Column
  private String profileImageUrl;

  @Column
  private String company;

  @Column
  private Boolean isDeleted;

  @Column
  private Integer rating;

  @Column
  private String bio;

  @Column
  private String refreshToken;

  @Builder
  private Member(OauthProvider oauthProvider, String oauthId, String nickname,
      String email, String profileImageUrl, String company, Boolean isDeleted,
      Integer rating, String bio, String refreshToken) {
    this.oauthProvider = oauthProvider;
    this.oauthId = oauthId;
    this.nickname = nickname;
    this.email = email;
    this.profileImageUrl = profileImageUrl;
    this.company = company;
    this.isDeleted = isDeleted;
    this.rating = rating;
    this.bio = bio;
    this.refreshToken = refreshToken;
  }

  public static Member of(OauthProvider oauthProvider, String oauthId, String nickname,
      String email, String profileImageUrl, String company, String bio) {
    return Member.builder()
                 .oauthProvider(oauthProvider)
                 .oauthId(oauthId)
                 .nickname(nickname)
                 .email(email)
                 .profileImageUrl(profileImageUrl)
                 .company(company)
                 .bio(bio)
                 .isDeleted(false)
                 .rating(0)
                 .build();
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
