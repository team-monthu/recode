package com.monthu.recode.domain.feed.infra.database;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.infra.database.jpa.FeedJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepository {

  private final FeedJpaRepository feedJpaRepository;

  @Override
  public Feed save(Feed feed) {
    return feedJpaRepository.save(feed);
  }

  public Optional<Feed> findById(Long id){
    return feedJpaRepository.findById(id);
  }

}
