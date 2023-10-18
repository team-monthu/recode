package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.application.repository.FeedScrapRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedScrap;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.domain.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedScrapCreateUseCase {

    private final FeedScrapRepository feedScrapRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public void createScrap(Long feedId) {
        // 임시 멤버 객체
        Member member = Member.builder()
                .bio("멤버소개")
                .company("삼성")
                .email("abc@abc.com")
                .nickname("아이유")
                .oauthId("abc")
                .oauthProvider(OauthProvider.KAKAO)
                .profileImageUrl("abc")
                .build();
        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedNotFoundException::new);

        feedScrapRepository.save(FeedScrap.builder()
                .feed(feed)
                .member(member)
                .build());
    }
}
