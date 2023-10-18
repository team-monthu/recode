package com.monthu.recode.domain.techStack.infra.database.jpa;

import com.monthu.recode.domain.techStack.domain.TechStack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackJpaRepository extends JpaRepository<TechStack, Long> {

  List<TechStack> findByIdIn(List<Long> stackIds);
}
