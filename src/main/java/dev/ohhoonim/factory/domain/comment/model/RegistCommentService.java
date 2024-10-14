package dev.ohhoonim.factory.domain.comment.model;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.ohhoonim.factory.domain.comment.model.port.CommentCommandPort;
import dev.ohhoonim.factory.domain.comment.model.port.CommentQueryPort;
import dev.ohhoonim.factory.domain.comment.model.port.MentionedCommentCommandPort;
import dev.ohhoonim.factory.domain.comment.service.exception.RequiredException;

@Service
public class RegistCommentService  {

    private final CommentQueryPort commentQueryPort;
    private final CommentCommandPort commentCommandPort;
    private final MentionedCommentCommandPort mentionedCommentCommandPort;

    public RegistCommentService(CommentQueryPort commentQueryPort,
            CommentCommandPort commentCommandPort,
            MentionedCommentCommandPort mentionedCommentCommandPort) {
        this.commentQueryPort = commentQueryPort;
        this.commentCommandPort = commentCommandPort;
        this.mentionedCommentCommandPort = mentionedCommentCommandPort;
    }

    public BlockComment lookupComment(String blockUuid) {
        List<Comment> comments = commentQueryPort.findByBlockUuid(blockUuid);
        return new BlockComment(blockUuid, comments);
    }

    public BlockComment registComment(Comment newComment) {
        verifyComment(newComment);
        commentCommandPort.registComment(newComment);
        if (isMentioned(newComment.contents())) {
            // TODO mentioned comment를 저장하는 로직 다시 생각해봐야함.
            // TODO @로 대상자 가져오는 로직 추가해야함.
            // TODO @별로 저장해야함.
            mentionedCommentCommandPort
                    .storeMentionedComment(new MentionedComment(newComment.author(), List.of(newComment)));
        }
        return lookupComment(newComment.blockUuid());
    }

    private void verifyComment(Comment newComment) {
        if (!StringUtils.hasText(newComment.blockUuid()) &&
                !StringUtils.hasText(newComment.author()) &&
                !StringUtils.hasText(newComment.title()) &&
                !StringUtils.hasText(newComment.contents())) {
            throw new RequiredException("필수값 누락", null);
        }
    }

    private Boolean isMentioned(String contents) {
        return StringUtils.hasText(contents) && contents.contains("@");
    }

}
