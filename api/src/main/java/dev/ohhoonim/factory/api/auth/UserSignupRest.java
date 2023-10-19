package dev.ohhoonim.factory.api.auth;

import java.util.function.Function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.factory.api.auth.dto.UserSignupRequest;
import dev.ohhoonim.factory.domain.auth.api.UserSignupUsecase;
import dev.ohhoonim.factory.domain.auth.api.service.UserSignupCommand;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserSignupRest {

    private final UserSignupUsecase userSignupService;

    @PostMapping("/checkRequiredItem")
    public void checkRequiredItem(@RequestBody UserSignupRequest dto) {
        userSignupService.checkRequiredItem(commandMapper.apply(dto));
    }

    @PostMapping("/requestSignup")
    public void requestSignup(@RequestBody UserSignupRequest dto) {
        userSignupService.requestSignup(commandMapper.apply(dto));
    }

    @PostMapping("/resendMai")
    public void resendMail(@RequestBody String email) {
        userSignupService.resendMail(email);
    }

    Function<UserSignupRequest, UserSignupCommand> commandMapper = req -> new UserSignupCommand(
            req.email(),
            req.password(),
            req.passwordVerify(),
            req.name());

}
