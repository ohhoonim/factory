package dev.ohhoonim.factory.api.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import dev.ohhoonim.factory.api.auth.dto.AuthRequest;
import dev.ohhoonim.factory.api.auth.dto.AuthResponse;
import dev.ohhoonim.factory.api.auth.service.AuthService;
import dev.ohhoonim.factory.api.auth.service.vo.AuthVo;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRest {
    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인 후 토큰 발급", tags = { "로그인" })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        Users user = Users.builder()
            .email(login.email()) 
            .password(login.password())
            .build(); 
        AuthVo authVo = authService.authenticate(user);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authVo.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(604800)
            .domain("localhost")
            .build();
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .body(new AuthResponse(authVo.accessToken()));
    }

    @Operation(summary = "토큰 재발급", description = "토큰 만료시 재발급해줌. 리프레쉬 토큰은 쿠키에 담겨있음", tags = { "로그인" })
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) throws IOException {
        String newAccessToken = "";
        Optional<String> refreshedAccessToken = authService.refreshToken(refreshToken, response);
        if (refreshedAccessToken.isPresent()) {
            newAccessToken = refreshedAccessToken.get();
        } 
        return ResponseEntity.ok()
            .body(new AuthResponse(newAccessToken));
    }

    // logout은 SecurityConfig에 "/auth/logout" 으로 설정되어있음
}
