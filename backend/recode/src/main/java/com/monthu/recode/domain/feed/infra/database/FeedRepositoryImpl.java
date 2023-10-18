package com.monthu.recode.domain.feed.infra.database;

import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.infra.database.jpa.FeedJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedRepositoryImpl implements FeedRepository {

  private final FeedJpaRepository feedJpaRepository;

  @Override
  @Transactional
  public Feed save(Feed feed) {
    return feedJpaRepository.save(feed);
  }

  @Override
  public Optional<Feed> findById(Long id){
    return feedJpaRepository.findById(id);
  }

  @Override
  public Page<Feed> findAll(Pageable pageable){
    return feedJpaRepository.findAllByOrderByModifiedAtDesc(pageable);
  }

}
