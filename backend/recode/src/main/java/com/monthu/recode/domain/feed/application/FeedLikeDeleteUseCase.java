package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedLikeRepository;
import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.FeedLike;
import com.monthu.recode.domain.feed.exception.FeedLikeNotFoundException;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.domain.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedLikeDeleteUseCase {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public void deleteLike(Long feedId) {
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

        FeedLike feedLike = feedLikeRepository.findByMemberAndFeed(member, feed)
                .orElseThrow(FeedLikeNotFoundException::new);

    }
}
