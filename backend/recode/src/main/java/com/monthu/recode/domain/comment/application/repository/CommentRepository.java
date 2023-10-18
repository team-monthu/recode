package com.monthu.recode.domain.comment.application.repository;

import com.monthu.recode.domain.comment.domain.Comment;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findById(Long id);

    Integer countByFeedId(Long feedId);

}
