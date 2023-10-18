package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.dto.ModifyFeedReqDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.feed.infra.database.FeedRepositoryImpl;
import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.domain.techStack.infra.database.TechStackRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedModifyUseCase {

    private final FeedRepositoryImpl feedRepository;
    private final TechStackRepositoryImpl techStackRepository;

    // todo 수정시 기존 태그와 신규 태그 카운트 갱신
    @Transactional
    public void modifyFeed(ModifyFeedReqDto modifyFeedReqDto) {
        List<TechStack> techStacks = techStackRepository.findByIdIn(modifyFeedReqDto.getStackIds());

        feedRepository.findById(modifyFeedReqDto.getId()).map(
                        feed -> {
                            feed.updateFeed(modifyFeedReqDto.getTitle(), modifyFeedReqDto.getMarkdown(),
                                    techStacks);
                            return feed;
                        }
                )
                .orElseThrow(FeedNotFoundException::new);
    }
}
