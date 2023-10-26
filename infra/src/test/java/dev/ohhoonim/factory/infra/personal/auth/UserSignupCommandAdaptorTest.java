package dev.ohhoonim.factory.infra.personal.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import dev.ohhoonim.factory.domain.auth.User;
import dev.ohhoonim.factory.infra.config.BusinessDatasourceConfig;
import dev.ohhoonim.factory.infra.config.PersonalDatasourceConfig;
import dev.ohhoonim.factory.infra.config.QueryDslConfig;

@DataJpaTest
@ImportAutoConfiguration({ PersonalDatasourceConfig.class,
        BusinessDatasourceConfig.class, QueryDslConfig.class, UserSignupCommandAdaptor.class })
// @Import({ UserSignupCommandAdaptor.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Profile("unittest")
public class UserSignupCommandAdaptorTest {

    @Autowired
    UserSignupCommandAdaptor userSignupCommandAdaptor;

    @Test
    void addUserTest() {
        User newUser = User.builder()
                .email("matthew.ju@ohhoonim.dev")
                .password("1234")
                .name("matthew")
                .build();

        Optional<User> addedUser = userSignupCommandAdaptor.addUser(newUser);

        assertTrue(addedUser.isPresent());
    }

}
