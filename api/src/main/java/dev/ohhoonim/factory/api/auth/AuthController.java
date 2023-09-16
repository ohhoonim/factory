package dev.ohhoonim.factory.api.auth;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhoonim.factory.api.auth.dto.AuthRequest;
import dev.ohhoonim.factory.api.auth.dto.AuthResponse;
import dev.ohhoonim.factory.api.auth.service.AuthService;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        User user = User.builder()
            .email(login.email()) 
            .password(login.password())
            .build(); 
        return ResponseEntity.ok(authService.authenticate(user));
        // authority : 회원가입 , 인가 
        // authentication : 로그인 , 인증 
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
