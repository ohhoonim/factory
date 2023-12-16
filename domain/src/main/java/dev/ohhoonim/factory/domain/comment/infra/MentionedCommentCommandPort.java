package dev.ohhoonim.factory.domain.comment.infra;

import dev.ohhoonim.factory.domain.comment.MentionedComment;

public interface MentionedCommentCommandPort {
    public void storeMentionedComment(MentionedComment mentionedComment);
}
