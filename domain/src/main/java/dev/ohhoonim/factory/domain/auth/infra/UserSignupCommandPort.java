package dev.ohhoonim.factory.domain.auth.infra;

import java.util.Optional;

import dev.ohhoonim.factory.domain.auth.User;

public interface UserSignupCommandPort {

    Optional<User> addUser(User newUser);
    
}
