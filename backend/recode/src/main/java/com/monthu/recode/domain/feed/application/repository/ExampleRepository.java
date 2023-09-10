package com.monthu.recode.domain.feed.application.repository;

import com.monthu.recode.domain.feed.domain.Example;
import java.util.Optional;

public interface ExampleRepository {

    Optional<Example> findById(Long id);

}
