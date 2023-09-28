package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.domain.feed.infra.database.converter.ContentsConverterImpl;
import com.monthu.recode.global.entity.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "feed")
@Where(clause = "is_deleted = false")
@Getter
@NoArgsConstructor
public class Feed extends BaseTimeEntity {

  @Builder
  public Feed(Long id, String title, String markdown, String html, Long writerId, List<Long> ids) {
    this.id = id;
    this.title = title;
    this.contents = new Contents(markdown, html);
    this.writerId = writerId;
    this.stacks = new ArrayList<>();
    for (Long stackId : ids) {
      TechStack techStack = new TechStack(stackId);
      stacks.add(techStack);
    }
  }

  @Id
  @Column(name = "feed_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  @Convert(converter = ContentsConverterImpl.class)
  private Contents contents;

  @Column
  private Long writerId;

  @Column
  private Integer viewCount = 0;

  @Column
  private Long adoptedCommentId;

  @Column
  private Boolean isDeleted = Boolean.FALSE;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "feed_stacks",
      joinColumns = @JoinColumn(name = "feed_id"))
  private List<TechStack> stacks = new ArrayList<>();

  @Embeddable
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class TechStack {
    private Long tech_stacks_id;

    @Column(name = "stack_id")
    private Long stackId;

    public TechStack(Long stackId) {
      this.stackId = stackId;
    }

  }

}
