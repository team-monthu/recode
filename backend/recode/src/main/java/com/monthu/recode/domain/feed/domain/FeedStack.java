package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.global.entity.BaseTimeEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feed_stack")
@Getter
@NoArgsConstructor
public class FeedStack extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "feed_id")
  private Feed feed;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  private TechStack techStack;

  @Builder
  public FeedStack(Feed feed, TechStack techStack) {
    this.feed = feed;
    this.techStack = techStack;
  }

  public static List<FeedStack> createFeedStacks(List<TechStack> stacks, Feed feed) {
    return stacks.stream()
                 .map(stack -> {
                   return FeedStack.builder()
                                   .feed(feed)
                                   .techStack(stack)
                                   .build();
                 })
                 .collect(Collectors.toList());
  }
}
