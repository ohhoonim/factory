package dev.ohhoonim.factory.infra.personal.auth;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import dev.ohhoonim.factory.domain.auth.User;
import dev.ohhoonim.factory.domain.auth.infra.UserSignupCommandPort;
import dev.ohhoonim.factory.infra.personal.auth.repository.UserRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Users;

@Component
public class UserSignupCommandAdaptor implements UserSignupCommandPort {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserSignupCommandAdaptor (UserRepository userRepository) {
        this.userRepository = userRepository;   
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Optional<User> addUser(User newUser) {
        Users users = userRepository.save(usersMapper.apply(newUser));
        return ObjectUtils.isEmpty(users) ? Optional.empty() : Optional.of(userMapper.apply(users));
    }

    Function<User, Users> usersMapper = user -> Users.builder()
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .password(user.getPassword())
            .name(user.getName())
            .build();

    Function<Users, User> userMapper = users -> User.builder()
            .id(users.getUserId())
            .email(users.getEmail())
            .name(users.getName())
            .build();
}
