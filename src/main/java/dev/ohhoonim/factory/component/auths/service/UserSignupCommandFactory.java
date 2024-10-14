package dev.ohhoonim.factory.component.auths.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import dev.ohhoonim.factory.component.auths.model.User;
import dev.ohhoonim.factory.component.auths.model.port.UserSignupCommandPort;
import dev.ohhoonim.factory.personalRepository.UserRepository;
import dev.ohhoonim.factory.personalTable.Users;

@Component
public class UserSignupCommandFactory implements UserSignupCommandPort {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserSignupCommandFactory (UserRepository userRepository) {
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
            .name(user.getName())
            .build();

    Function<Users, User> userMapper = users -> User.builder()
            .id(users.getUserId())
            .email(users.getEmail())
            .name(users.getName())
            .build();
}
