package com.monthu.recode.domain.comment.infra.database.jpa;

import com.monthu.recode.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Integer countByFeedId(Long feedId);

}
