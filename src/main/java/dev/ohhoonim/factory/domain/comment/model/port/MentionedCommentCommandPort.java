package dev.ohhoonim.factory.domain.comment.model.port;

import dev.ohhoonim.factory.domain.comment.model.MentionedComment;

public interface MentionedCommentCommandPort {
    public void storeMentionedComment(MentionedComment mentionedComment);
}
