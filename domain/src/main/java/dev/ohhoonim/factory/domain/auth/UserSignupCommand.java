package dev.ohhoonim.factory.domain.auth;

import lombok.Builder;

@Builder
public record UserSignupCommand(
        String email,
        String password,
        String passwordVerify,
        String name) {

}
