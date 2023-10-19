package dev.ohhoonim.factory.api.auth.dto;

public record UserSignupRequest(String email,
        String password,
        String passwordVerify,
        String name) {

}
