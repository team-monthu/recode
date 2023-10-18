package com.monthu.recode.domain.feed.application;

import com.monthu.recode.domain.comment.application.repository.CommentRepository;
import com.monthu.recode.domain.comment.domain.Comment;
import com.monthu.recode.domain.comment.exception.CommentNotFoundException;
import com.monthu.recode.domain.feed.application.repository.FeedLikeRepository;
import com.monthu.recode.domain.feed.application.repository.FeedRepository;
import com.monthu.recode.domain.feed.domain.Feed;
import com.monthu.recode.domain.feed.dto.CommentResDto;
import com.monthu.recode.domain.feed.dto.FindFeedDetailsResDto;
import com.monthu.recode.domain.feed.dto.FindFeedDetailsWithMarkdownResDto;
import com.monthu.recode.domain.feed.dto.MemberResDto;
import com.monthu.recode.domain.feed.exception.FeedNotFoundException;
import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedSearchUseCase {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final CommentRepository commentRepository;


    public FindFeedDetailsResDto searchFeedDetailsById(Long feedId) {
        Feed foundFeed = feedRepository.findById(feedId).map(
                feed -> {
                    updateViewCount(feed, Feed::increaseViewCount);
                    return feed;
                })
                .orElseThrow(FeedNotFoundException::new);

        Member feedWriter = memberRepository.findById(foundFeed.getWriterId())
                .orElseThrow(MemberNotFoundException::new);

        Integer commentCount = commentRepository.countByFeedId(feedId);

        CommentResDto commentResDto = null;
        if(foundFeed.getAdoptedCommentId() != null){
            Comment adoptedComment = commentRepository.findById(foundFeed.getAdoptedCommentId())
                    .orElseThrow(CommentNotFoundException::new);
            Member commentWriter = memberRepository.findById(adoptedComment.getWriterId())
                    .orElseThrow(MemberNotFoundException::new);
            MemberResDto writer = MemberResDto.from(commentWriter);
            commentResDto = CommentResDto.of(adoptedComment, writer);
        }

        return FindFeedDetailsResDto.builder()
                .feed(foundFeed)
                .writer(MemberResDto.from(feedWriter))
                .likeCount(foundFeed.getLikeCount())
                .commentCount(commentCount)
                .adoptedComment(commentResDto)
                .build();
    }

    public FindFeedDetailsWithMarkdownResDto searchFeedDetailsWithMarkdown(Long feedId) {
        Feed foundFeed = feedRepository.findById(feedId).orElseThrow(FeedNotFoundException::new);
        return FindFeedDetailsWithMarkdownResDto.from(foundFeed);
    }

    @Transactional
    public void updateViewCount(Feed feed, ChangeCountConsumer<Feed> consumer) {
        consumer.changeCount(feed);
        feedRepository.save(feed);
    }

    public Page<Feed> searchMainFeeds(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

}
