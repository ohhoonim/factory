package dev.ohhoonim.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class FactoryTest {
    @Test
	void contextLoads() {
	}

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void generatePassword() {
        log.info("===<sysadm>{}</sysadm>===", passwordEncoder.encode("1234"));
    }
}
