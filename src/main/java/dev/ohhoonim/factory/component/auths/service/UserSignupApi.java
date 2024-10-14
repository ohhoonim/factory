package dev.ohhoonim.factory.component.auths.service;

import java.util.function.Function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.factory.component.auths.model.UserSignupService;
import dev.ohhoonim.factory.component.auths.service.dto.UserSignupRequest;
import dev.ohhoonim.factory.component.auths.service.vo.UserSignupCommand;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserSignupApi {

    private final UserSignupService userSignupService;

    @Operation(summary = "회원가입 필수값 체크", description = "이메일, 패스워드를 체크한다.", tags = { "회원가입" })
    @PostMapping("/checkRequiredItem")
    public void checkRequiredItem(@RequestBody UserSignupRequest dto) {
        userSignupService.checkRequiredItem(commandMapper.apply(dto));
    }

    @Operation(summary = "회원가입 요청", description = "이메일, 패스워드 유효성 검사 후 확인 메일 발송", tags = { "회원가입" })
    @PostMapping("/requestSignup")
    public void requestSignup(@RequestBody UserSignupRequest dto) {
        userSignupService.requestSignup(commandMapper.apply(dto));
    }

    @Operation(summary = "이메일 재전송", description = "회원가입시 이메일을 받지못했을 경우 재발송 기능", tags = { "회원가입" })
    @PostMapping("/resendMail")
    public void resendMail(@RequestBody String email) {
        userSignupService.resendMail(email);
    }

    Function<UserSignupRequest, UserSignupCommand> commandMapper = req -> new UserSignupCommand(
            req.email(),
            req.password(),
            req.passwordVerify(),
            req.name());

}
