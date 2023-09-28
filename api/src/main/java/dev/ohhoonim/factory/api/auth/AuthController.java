package dev.ohhoonim.factory.api.auth;

import java.io.IOException;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import dev.ohhoonim.factory.api.auth.dto.AuthRequest;
import dev.ohhoonim.factory.api.auth.dto.AuthResponse;
import dev.ohhoonim.factory.api.auth.service.AuthService;
import dev.ohhoonim.factory.api.auth.service.vo.AuthVo;
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
        AuthVo authVo = authService.authenticate(user);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authVo.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(60)
            .domain("localhost")
            .build();
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .body(new AuthResponse(authVo.accessToken()));
    }

    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

    // logout은 SecurityConfig에 "/auth/logout" 으로 설정되어있음
}
