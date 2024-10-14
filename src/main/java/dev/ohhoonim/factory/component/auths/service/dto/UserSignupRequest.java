package dev.ohhoonim.factory.component.auths.service.dto;

public record UserSignupRequest(String email,
        String password,
        String passwordVerify,
        String name) {

}
