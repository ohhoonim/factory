package dev.ohhoonim.factory.infra.personal.auth;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.ohhoonim.factory.domain.auth.infra.SmtpPort;

@SpringBootTest
public class SmtpAdaptorTest {

    @Autowired
    SmtpPort smtpAdaptor;

    @Test
    public void sendTest() {
        assertThrowsExactly(UnsupportedOperationException.class,
                () -> smtpAdaptor.send("matthew@ohhoonim.com"),
                "");
    }
}
