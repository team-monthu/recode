package com.monthu.recode.domain.member.domain;

import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.global.entity.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  private Boolean isDeleted = false;

  @Column
  private Integer rating = 0;

  @Column
  private String bio;

  @Column
  private String refreshToken;

  @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
  private List<MemberStack> memberStacks = new ArrayList<>();

  @Builder
  private Member(OauthProvider oauthProvider, String oauthId, String nickname,
      String email, String profileImageUrl, String company, String bio, String refreshToken,
      List<TechStack> stacks) {
    this.oauthProvider = oauthProvider;
    this.oauthId = oauthId;
    this.nickname = nickname;
    this.email = email;
    this.profileImageUrl = profileImageUrl;
    this.company = company;
    this.bio = bio;
    this.refreshToken = refreshToken;
    List<MemberStack> memberStacks = MemberStack.createMemberStacks(stacks, this);
    this.memberStacks = memberStacks;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
