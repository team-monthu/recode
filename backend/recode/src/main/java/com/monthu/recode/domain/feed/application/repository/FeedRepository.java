package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import java.util.Optional;

public interface FeedRepository {

  Feed save(Feed feed);
}
