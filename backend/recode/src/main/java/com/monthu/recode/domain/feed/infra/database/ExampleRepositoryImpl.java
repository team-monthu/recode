package com.monthu.recode.domain.feed.infra.database;

import com.monthu.recode.domain.feed.application.repository.ExampleRepository;
import com.monthu.recode.domain.feed.domain.Example;
import com.monthu.recode.domain.feed.infra.database.jpa.ExampleJpaRepository;
import com.monthu.recode.domain.feed.infra.database.querydsl.ExampleQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExampleRepositoryImpl implements ExampleRepository{
    private final ExampleJpaRepository exampleJpaRepository;
    private final ExampleQueryRepository exampleQueryRepository;

    @Override
    public Optional<Example> findById(Long id) {
        return exampleJpaRepository.findById(id);
    }

}
