package com.monthu.recode.domain.feed.dto;

import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.domain.Feed.TechStack;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ModifyFeedDto {

    private Long id;

    @NotBlank
    @Size(max = 20)
    private String title;

    @NotBlank
    @NotNull
    private String markdown;
    private List<Long> stacks;

}
