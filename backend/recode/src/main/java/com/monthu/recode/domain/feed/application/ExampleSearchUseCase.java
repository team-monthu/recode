package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.feed.application.repository.ExampleRepository;
import com.monthu.recode.domain.feed.domain.Example;
import com.monthu.recode.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExampleSearchUseCase {

    private final ExampleRepository exampleRepository;

    @Transactional(readOnly = true)
    public Example searchExampleById(Long id) {
        return exampleRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("찾을 수 없습니다."));
    }

}
