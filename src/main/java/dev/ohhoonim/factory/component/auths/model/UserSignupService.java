package dev.ohhoonim.factory.component.auths.model;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import dev.ohhoonim.factory.component.auths.model.exception.RequiredItemException;
import dev.ohhoonim.factory.component.auths.model.port.UserSignupCommandPort;
import dev.ohhoonim.factory.component.auths.service.exception.FactoryUserException;
import dev.ohhoonim.factory.component.auths.service.vo.UserSignupCommand;
import dev.ohhoonim.factory.component.smtp.SmtpPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSignupService {

    private final UserSignupCommandPort userSignupCommandPort;
    private final SmtpPort smtpPort;

    /**
     * 회원가입 링크 선택 
     * 
     * <ol>
     * <li>사용자 정보 입력됨</li>
     * </ol>
     * 
     * @param command
     */
    public void checkRequiredItem(UserSignupCommand command) {
        if (ObjectUtils.isEmpty(command) ||
                !StringUtils.hasText(command.email()) ||
                !StringUtils.hasText(command.password()) ||
                !StringUtils.hasText(command.passwordVerify())) {
            throw new RequiredItemException("입력값이 존재하지 않습니다.");
        }
    }

    /**
     * 회원 생성 요청
     * 
     * <ol>
     * <li>회원정보 유효서 확인됨</li>
     * <li>회원 계정 생성됨</li>
     * <li>인증 메일 발송됨-메일발송 실패에 대한 예외처리는 하지 않는다.</li>
     * </ol>
     * 
     * @param command
     */
    public void requestSignup(UserSignupCommand command) {
        verifyCommand(command);
        Optional<User> addedUser = Optional.empty();
        try{ 
            addedUser = userSignupCommandPort.addUser(userMapper.apply(command));
        } catch (Exception e) {
            throw new FactoryUserException("이미 등록된 메일 주소입니다.");
        }
        
        try {
            smtpPort.send(addedUser.get().getEmail());
        } catch (UnsupportedOperationException e) {
            log.warn("메일 기능은 동작하지 않습니다.");
        }
    }

    /**
     * 인증메일 재전송
     */
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
