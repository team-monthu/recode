package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.domain.feed.infra.database.converter.ContentsConverter;
import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.global.entity.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

  @Id
  @Column(name = "feed_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  @Convert(converter = ContentsConverter.class)
  private Contents contents;

  @Column
  private Long writerId;

  @Column
  private Integer viewCount = 0;

  @Column
  private Long adoptedCommentId;

  @Column
  private Boolean isDeleted = Boolean.FALSE;

  @OneToMany(mappedBy = "feed", cascade = CascadeType.PERSIST)
  private List<FeedStack> feedStacks = new ArrayList<>();

  @Builder
  public Feed(String title, String markdown, Long writerId, List<TechStack> stacks) {
    this.title = title;
    this.contents = new Contents(markdown);
    this.writerId = writerId;
    List<FeedStack> feedStacks = FeedStack.createFeedStacks(stacks, this);
    this.feedStacks = feedStacks;
  }
}
