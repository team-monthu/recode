package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.global.entity.BaseTimeEntity;
import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "feed")
@Getter
public class Feed extends BaseTimeEntity {


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
  private int viewCount;

  @Column
  private Long adoptedCommentId;

  @Column
  private boolean isDeleted;

}
