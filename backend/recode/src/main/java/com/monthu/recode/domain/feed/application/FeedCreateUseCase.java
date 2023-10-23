package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.domain.feed.dto.WriteFeedResDto;
import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.techStack.application.repository.TechStackRepository;
import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.global.config.webmvc.AuthMember;
import com.monthu.recode.global.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedCreateUseCase {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final TechStackRepository techStackRepository;

    @Transactional
    public WriteFeedResDto writeFeed(WriteFeedReqDto writeFeedReqDto, AuthMember authMember) {
        Member writer = memberRepository.findById(authMember.getId())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                "멤버를 찾을 수 없습니다."));
        List<TechStack> stacks = techStackRepository.findByIdIn(
                writeFeedReqDto.getStackIds());
        increaseTaggedCount(stacks);
        Feed feed = feedRepository.save(
                writeFeedReqDto.createFeedWithStacksAndWriter(stacks, writer));
        return new WriteFeedResDto(feed.getId());
    }

    private void increaseTaggedCount(List<TechStack> stacks) {
        stacks.forEach(TechStack::tagged);
    }
}
