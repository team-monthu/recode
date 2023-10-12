package com.monthu.recode.domain.member.domain;

import com.monthu.recode.domain.techStack.domain.TechStack;
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
@Table(name = "member_stack")
@Getter
@NoArgsConstructor
public class MemberStack {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_stack_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  private TechStack techStack;

  @Builder
  public MemberStack(Member member, TechStack techStack) {
    this.member = member;
    this.techStack = techStack;
  }

  public static List<MemberStack> createMemberStacks(List<TechStack> stacks, Member member) {
    return stacks.stream()
                 .map(stack -> {
                   return MemberStack.builder()
                                     .member(member)
                                     .techStack(stack)
                                     .build();
                 })
                 .collect(Collectors.toList());
  }
}
