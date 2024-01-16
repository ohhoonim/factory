package dev.ohhoonim.factory.infra.business.comment;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.domain.comment.Comment;
import dev.ohhoonim.factory.domain.comment.MentionedComment;
import dev.ohhoonim.factory.domain.comment.infra.CommentCommandPort;
import dev.ohhoonim.factory.domain.comment.infra.CommentQueryPort;
import dev.ohhoonim.factory.domain.comment.infra.MentionedCommentCommandPort;

@Component
public class CommentAdaptor implements CommentQueryPort, CommentCommandPort, MentionedCommentCommandPort {

    @Override
    public void storeMentionedComment(MentionedComment mentionedComment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeMentionedComment'");
    }

    @Override
    public void registComment(Comment newComment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registComment'");
    }

    @Override
    public List<Comment> findByBlockUuid(String blockUuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByBlockUuid'");
    }
    
}
