package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Feed;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedRepository {

  Feed save(Feed feed);

  Optional<Feed> findById(Long feedId);

  Page<Feed> findAll(Pageable pageable);
}
