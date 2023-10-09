package com.monthu.recode.domain.feed.infra.database.jpa;

import com.monthu.recode.domain.feed.domain.Feed;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {

    Page<Feed> findAllByOrderByModifiedAtDesc(Pageable pageable);

}
