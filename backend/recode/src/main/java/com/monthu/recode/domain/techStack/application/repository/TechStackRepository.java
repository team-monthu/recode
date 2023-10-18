package com.monthu.recode.domain.techStack.application.repository;

import com.monthu.recode.domain.techStack.domain.TechStack;
import java.util.List;

public interface TechStackRepository {

  List<TechStack> findByIdIn(List<Long> stackIds);
}
