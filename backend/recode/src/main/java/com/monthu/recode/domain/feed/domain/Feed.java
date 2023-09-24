package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.global.entity.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  @Builder
  public Feed(Long id, String title, String content, Long writerId, Integer viewCount,
      Long adoptedCommentId, Boolean isDeleted, List<Long> ids) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.writerId = writerId;
    this.viewCount = viewCount;
    this.adoptedCommentId = adoptedCommentId;
    this.isDeleted = isDeleted;
    this.stacks = new ArrayList<>();
    for(Long stackId : ids){
      TechStack techStack = new TechStack(id);
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
  private String content;

  @Column
  private Long writerId;

  @Column
  private Integer viewCount;

  @Column
  private Long adoptedCommentId;

  @Column
  private Boolean isDeleted = Boolean.FALSE;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "fedd_stacks",
      joinColumns = @JoinColumn(name = "feed_id"))
  private List<TechStack> stacks;

  @Embeddable
  public class TechStack {

    private Long stackId;

    public TechStack(Long stackId){
      this.stackId = stackId;
    }
  }

}
