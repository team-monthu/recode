package com.monthu.recode.domain.feed.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import com.monthu.recode.global.config.webmvc.AuthMember;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedCreateUseCaseTest {

    @InjectMocks
    FeedCreateUseCase feedCreateUseCase;
    @Mock
    FeedRepository feedRepository;
    WriteFeedReqDto writeFeedReqDto;


    @Test
    @DisplayName("피드를 생성할 수 있다.")
    void postFeedTest() {
        // given
        writeFeedReqDto = WriteFeedReqDto
                .builder()
                .title("피드 업로드 테스트")
                .markdown("I am **Iron man**")
                .stackIds(List.of(1L, 2L))
                .build();

        Feed feed = new Feed();
        AuthMember authMember = new AuthMember(1L);
        when(feedRepository.save(any(Feed.class))).thenReturn(feed);

        // when
        feedCreateUseCase.writeFeed(writeFeedReqDto, authMember);

        // then
        verify(feedRepository, times(1)).save(any(Feed.class));

    }

}