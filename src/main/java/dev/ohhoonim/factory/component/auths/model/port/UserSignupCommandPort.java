package dev.ohhoonim.factory.component.auths.model.port;

import java.util.Optional;

import dev.ohhoonim.factory.component.auths.model.User;

public interface UserSignupCommandPort {

    Optional<User> addUser(User newUser);
    
}
