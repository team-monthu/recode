package com.monthu.recode.domain.feed.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ModifyFeedReqDto {

    private Long id;

    @NotBlank
    @Size(max = 20)
    private String title;

    @NotBlank
    @NotNull
    private String markdown;
    private List<Long> stackIds;

}
