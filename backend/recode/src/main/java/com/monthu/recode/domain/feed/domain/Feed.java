package com.monthu.recode.domain.feed.domain;

import com.monthu.recode.domain.feed.dto.WriteFeedReqDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "feed")
@EqualsAndHashCode
public class Feed {

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

  public Feed of(WriteFeedReqDto writeFeedReqDto, Long memberId) {
    return Feed.builder()
        .title(writeFeedReqDto.getTitle())
        .content(writeFeedReqDto.getContent())
        .writerId(memberId)
        .build();
  }

}
