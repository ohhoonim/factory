package dev.ohhoonim.factory.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.domain.comment.model.Comment;
import dev.ohhoonim.factory.domain.comment.model.MentionedComment;
import dev.ohhoonim.factory.domain.comment.model.port.CommentCommandPort;
import dev.ohhoonim.factory.domain.comment.model.port.CommentQueryPort;
import dev.ohhoonim.factory.domain.comment.model.port.MentionedCommentCommandPort;

@Component
public class CommentFactory implements CommentQueryPort, CommentCommandPort, MentionedCommentCommandPort {

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
