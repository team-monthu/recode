package com.monthu.recode.domain.techStack.infra.database;

import com.monthu.recode.domain.techStack.application.repository.TechStackRepository;
import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.domain.techStack.infra.database.jpa.TechStackJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TechStackRepositoryImpl implements TechStackRepository {

  private final TechStackJpaRepository techStackJpaRepository;

  @Override
  public List<TechStack> findByIdIn(List<Long> stackIds) {
    return techStackJpaRepository.findByIdIn(stackIds);
  }
}
