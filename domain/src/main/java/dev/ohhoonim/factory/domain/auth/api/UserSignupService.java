package dev.ohhoonim.factory.domain.auth.api;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import dev.ohhoonim.factory.domain.auth.User;
import dev.ohhoonim.factory.domain.auth.UserSignupCommand;
import dev.ohhoonim.factory.domain.auth.UserSignupUsecase;
import dev.ohhoonim.factory.domain.auth.infra.FactoryUserException;
import dev.ohhoonim.factory.domain.auth.infra.SmtpPort;
import dev.ohhoonim.factory.domain.auth.infra.UserSignupCommandPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSignupService implements UserSignupUsecase {

    private final UserSignupCommandPort userSignupCommandPort;
    private final SmtpPort smtpPort;

    @Override
    public void checkRequiredItem(UserSignupCommand command) {
        if (ObjectUtils.isEmpty(command) ||
                StringUtils.hasText(command.email()) ||
                StringUtils.hasText(command.password()) ||
                StringUtils.hasText(command.passwordVerify())) {
            throw new RequiredItemException("입력값이 존재하지 않습니다.");
        }
    }

    @Override
    public void requestSignup(UserSignupCommand command) {
        verifyCommand(command);
        Optional<User> addedUser = userSignupCommandPort.addUser(userMapper.apply(command));
        if (!addedUser.isPresent()) {
            throw new FactoryUserException("회원등록에 실패하였습니다.");
        }
        smtpPort.send(addedUser.get().getEmail());
    }

    @Override
    public void resendMail(String email) {
        smtpPort.send(email);
    }

    private void verifyCommand(UserSignupCommand command) {
        String EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$";

        if (!Pattern.compile(EMAIL_REGEX).matcher(command.email()).matches()) {
            throw new RequiredItemException("이메일형식에 맞지 않습니다.");
        } else if (!Pattern.compile(PASSWORD_REGEX).matcher(command.password()).matches()) {
            throw new RequiredItemException("비밀번호는 문자,숫자 1개 이상 포함하며 8자 이상이어야 합니다. ");
        } else if (!command.password().equals(command.passwordVerify())) {
            throw new RequiredItemException("패스워드가 일치하지 않습니다.");
        }
    }

    Function<UserSignupCommand, User> userMapper = command -> User.builder()
            .email(command.email())
            .name(command.name())
            .password(command.password())
            .build();

}
