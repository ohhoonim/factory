package dev.ohhoonim.factory.infra.personal.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import dev.ohhoonim.factory.component.auths.model.User;
import dev.ohhoonim.factory.component.auths.service.UserSignupCommandFactory;
import dev.ohhoonim.factory.configuration.BusinessDatasourceConfig;
import dev.ohhoonim.factory.configuration.PersonalDatasourceConfig;
import dev.ohhoonim.factory.configuration.QueryDslConfig;
import dev.ohhoonim.factory.personalRepository.UserRepository;
import dev.ohhoonim.factory.personalTable.Users;

@DataJpaTest
@ImportAutoConfiguration({ PersonalDatasourceConfig.class,
        BusinessDatasourceConfig.class,
        QueryDslConfig.class,
        UserSignupCommandFactory.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Profile("unittest")
public class UserSignupCommandAdaptorTest {

    @Autowired
    UserSignupCommandFactory userSignupCommandAdaptor;

    @Autowired
    UserRepository userRepository;

    @Test
    void addUserTest() {
        User newUser = User.builder()
                .email("matthew.ju@ohhoonim.dev")
                .password("1234")
                .name("matthew")
                .build();

        Optional<User> savedUser = userSignupCommandAdaptor.addUser(newUser);

        assertTrue(savedUser.isPresent());

        userRepository.delete(usersTestMapper.apply(savedUser.get()));
    }

    Function<User, Users> usersTestMapper = user -> Users.builder()
            .userId(user.getId())
            .build();
}
