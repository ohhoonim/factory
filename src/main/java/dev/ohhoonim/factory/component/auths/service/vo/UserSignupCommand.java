package dev.ohhoonim.factory.component.auths.service.vo;

import lombok.Builder;

@Builder
public record UserSignupCommand(
        String email,
        String password,
        String passwordVerify,
        String name) {

}
