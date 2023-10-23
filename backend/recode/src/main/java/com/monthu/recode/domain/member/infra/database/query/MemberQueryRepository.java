package com.monthu.recode.domain.member.infra.database.query;


import com.monthu.recode.domain.feed.domain.QFeed;
import com.monthu.recode.domain.follow.domain.QFollow;
import com.monthu.recode.domain.member.domain.QMember;
import com.monthu.recode.domain.member.domain.QMemberStack;
import com.monthu.recode.domain.member.dto.MemberInfoProjectionDto;
import com.monthu.recode.domain.member.dto.QMemberInfoProjectionDto;
import com.monthu.recode.domain.techStack.domain.QTechStack;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private QMember member = QMember.member;
    private QMemberStack memberStack = QMemberStack.memberStack;
    private QTechStack techStack = QTechStack.techStack;
    private QFollow follow = QFollow.follow;
    private QFeed feed = QFeed.feed;

    public MemberInfoProjectionDto findMemberDetailsById(Long memberId) {
        return jpaQueryFactory.select(new QMemberInfoProjectionDto(member,
                                      countFeedsByMemberId(memberId),
                                      countFollowersByMemberId(memberId),
                                      countFollowingsByMemberId(memberId)))
                              .from(member)
                              .leftJoin(member.memberStacks, memberStack)
                              .fetchJoin()
                              .leftJoin(memberStack.techStack, techStack)
                              .fetchJoin()
                              .where(member.id.eq(memberId))
                              .distinct()
                              .fetchFirst();
    }

    private JPQLQuery<Integer> countFeedsByMemberId(Long memberId) {
        return JPAExpressions.select(member.feeds.size())
                             .from(member)
                             .where(member.id.eq(memberId));
    }

    private JPQLQuery<Integer> countFollowersByMemberId(Long memberId) {
        return JPAExpressions.select(member.followers.size())
                             .from(member)
                             .where(member.id.eq(memberId));
    }

    private JPQLQuery<Integer> countFollowingsByMemberId(Long memberId) {
        return JPAExpressions.select(member.followings.size())
                             .from(member)
                             .where(member.id.eq(memberId));
    }

}
