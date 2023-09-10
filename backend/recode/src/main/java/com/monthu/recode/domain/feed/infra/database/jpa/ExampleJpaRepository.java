package com.monthu.recode.domain.feed.infra.database.jpa;

import com.monthu.recode.domain.feed.domain.Example;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleJpaRepository extends JpaRepository<Example, Long> {
    Optional<Example> findById(Long id);
}
