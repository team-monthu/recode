package com.monthu.recode.domain.techStack.domain;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.monthu.recode.global.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "tech_stack")
@Getter
public class techStack extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private int taggedCount;

  @Column
  private String imageUrl;

}
