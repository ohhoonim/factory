package dev.ohhoonim.factory.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.ohhoonim.factory.domain.auth.User;
import dev.ohhoonim.factory.domain.auth.api.RequiredItemException;
import dev.ohhoonim.factory.domain.auth.api.service.UserSignupCommand;
import dev.ohhoonim.factory.domain.auth.api.service.UserSignupService;
import dev.ohhoonim.factory.domain.auth.infra.FactoryUserException;
import dev.ohhoonim.factory.domain.auth.infra.SmtpPort;
import dev.ohhoonim.factory.domain.auth.infra.UserSignupCommandPort;

@ExtendWith(MockitoExtension.class)
public class UserSignupUsecaseTest {

    @InjectMocks
    UserSignupService userSignupService;

    @Mock
    UserSignupCommandPort userSignupCommandPort;
    @Mock
    SmtpPort smtpPort;

    @ParameterizedTest
    @MethodSource("commandProvider")
    void checkRequiredItem(UserSignupCommand command, Boolean expect) {
        if( !expect) {
                assertThrowsExactly(RequiredItemException.class,
                        () -> userSignupService.checkRequiredItem(command), "");
        } else {
                assertDoesNotThrow(() -> userSignupService.checkRequiredItem(command));
        }
    }

    public static Stream<Arguments> commandProvider() {
        return Stream.of(
                arguments(UserSignupCommand.builder()
                        .email("matthew@ohhoonim.com").password("1234qwer")
                        .passwordVerify("1234qwer")
                        .build(), true),
                arguments(UserSignupCommand.builder()
                        .email(null).password("1234qwer")
                        .passwordVerify("1234qwer")
                        .build(), false),
                arguments(UserSignupCommand.builder()
                        .email("matthew@ohhoonim.com").password(null)
                        .passwordVerify("1234qwer")
                        .build(), false),
                arguments(UserSignupCommand.builder()
                        .email("matthew@ohhoonim.com").password("1234qwer")
                        .passwordVerify(null)
                        .build(), false),
                arguments(UserSignupCommand.builder()
                        .email("matthew@ohhoonim.com").password("1234qwer")
                        .passwordVerify("9991234qwer")
                        .build(), true));

    }

    @Test
    public void requestSignup_invalidEmail() {
        UserSignupCommand command = UserSignupCommand.builder()
                .email("matthewhhoonim.dev").password("1234#qwer")
                .passwordVerify("1234#qwer")
                .build();
        assertThrowsExactly(RequiredItemException.class,
                () -> userSignupService.requestSignup(command), "");
        verify(userSignupCommandPort, times(0)).addUser(any());
        verify(smtpPort, times(0)).send(any());
    }

    @Test
    public void requestSignup_invalidPassword() {
        UserSignupCommand command = UserSignupCommand.builder()
                .email("matthew@ohhoonim.dev").password("qawsedqwer")
                .passwordVerify("qawsedqwer")
                .build();
        assertThrowsExactly(RequiredItemException.class,
                () -> userSignupService.requestSignup(command), "");
        verify(userSignupCommandPort, times(0)).addUser(any());
        verify(smtpPort, times(0)).send(any());
    }

    @Test
    public void requestSignup_invalidPasswordVerify() {
        UserSignupCommand command = UserSignupCommand.builder()
                .email("matthew@ohhoonim.dev").password("1234dqwer")
                .passwordVerify("1234dqwer99")
                .build();
        assertThrowsExactly(RequiredItemException.class,
                () -> userSignupService.requestSignup(command), "");
        verify(userSignupCommandPort, times(0)).addUser(any());
        verify(smtpPort, times(0)).send(any());
    }

    @Test
    public void requestSignup_failAddUser() {
        UserSignupCommand command = UserSignupCommand.builder()
                .email("matthew@ohhoonim.dev").password("1234dqwer")
                .passwordVerify("1234dqwer")
                .build();
        assertThrowsExactly(FactoryUserException.class,
                () -> userSignupService.requestSignup(command), "");
        verify(userSignupCommandPort, times(1)).addUser(any());
        verify(smtpPort, times(0)).send(any());
    }

    @Test
    public void requestSignup_sendMail() {
        UserSignupCommand command = UserSignupCommand.builder()
                .email("matthew@ohhoonim.dev").password("1234dqwer")
                .passwordVerify("1234dqwer")
                .build();

        when(userSignupCommandPort.addUser(any())).thenReturn(Optional.of(User.builder().build()));

        userSignupService.requestSignup(command);
        
        verify(userSignupCommandPort, times(1)).addUser(any());
        verify(smtpPort, times(1)).send(any());
    }
}
