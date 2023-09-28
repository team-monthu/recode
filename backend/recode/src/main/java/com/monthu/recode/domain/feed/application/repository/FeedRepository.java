package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Feed;

public interface FeedRepository {

  Feed save(Feed feed);
}
