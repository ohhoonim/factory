package dev.ohhoonim.factory.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.ohhoonim.factory.domain.comment.model.RegistCommentService;
import dev.ohhoonim.factory.domain.comment.model.port.CommentCommandPort;
import dev.ohhoonim.factory.domain.comment.model.port.CommentQueryPort;
import dev.ohhoonim.factory.domain.comment.model.port.MentionedCommentCommandPort;

@ExtendWith(MockitoExtension.class)
public class RegistCommentAgentTest {
    @InjectMocks
    RegistCommentService registCommentAgent;

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
