package dev.ohhoonim.factory.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.ohhoonim.factory.domain.comment.api.RegistCommentAgent;
import dev.ohhoonim.factory.domain.comment.infra.CommentCommandPort;
import dev.ohhoonim.factory.domain.comment.infra.CommentQueryPort;
import dev.ohhoonim.factory.domain.comment.infra.MentionedCommentCommandPort;

@ExtendWith(MockitoExtension.class)
public class RegistCommentAgentTest {
    @InjectMocks
    RegistCommentAgent registCommentAgent;

    @Mock
    CommentQueryPort commentQueryPort;
    
    @Mock
    CommentCommandPort commentCommandPort;

    @Mock
    MentionedCommentCommandPort mentionedCommentCommandPort;

    @Test
    void lookupCommentTest() {
        // TODO
    }

    @Test
    void registComment() {
        // TODO
    }








    

}
