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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private OAuthProvider oAuthProvider;

    @Column
    private String oAuthId;

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

    @Builder
    private Member(OAuthProvider oAuthProvider, String oAuthId, String nickname,
            String email, String profileImageUrl, String company, Boolean isDeleted,
            Integer rating, String bio) {
        this.oAuthProvider = oAuthProvider;
        this.oAuthId = oAuthId;
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.company = company;
        this.isDeleted = isDeleted;
        this.rating = rating;
        this.bio = bio;
    }

    public static Member of(OAuthProvider oAuthProvider, String oAuthId, String nickname,
            String email, String profileImageUrl, String company, String bio) {
        return Member.builder()
                     .oAuthProvider(oAuthProvider)
                     .oAuthId(oAuthId)
                     .nickname(nickname)
                     .email(email)
                     .profileImageUrl(profileImageUrl)
                     .company(company)
                     .bio(bio)
                     .isDeleted(false)
                     .rating(0)
                     .build();
    }

}
