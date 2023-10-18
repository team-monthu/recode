package com.monthu.recode.domain.comment.infra.database.jpa;

import com.monthu.recode.domain.comment.application.repository.CommentRepository;
import com.monthu.recode.domain.comment.domain.Comment;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;


    @Override
    public Optional<Comment> findById(Long id) {
        return commentJpaRepository.findById(id);
    }

    @Override
    public Integer countByFeedId(Long feedId) {
        return commentJpaRepository.countByFeedId(feedId);
    }
}
