package dev.ohhoonim.factory.api.auth.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import dev.ohhoonim.factory.api.auth.service.vo.AuthVo;
import dev.ohhoonim.factory.api.config.JwtService;
import dev.ohhoonim.factory.infra.personal.auth.repository.TokenRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.UserRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Tokens;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.User;
import dev.ohhoonim.factory.infra.personal.auth.repository.type.TokenType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthVo authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveToken(user, jwtToken);
        saveToken(user, refreshToken);
        return new AuthVo(jwtToken, refreshToken);
    }

    private void revokeAllUserTokens(User user) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserId(user.getEmail());
        if (!validTokens.isEmpty()) {
            validTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
                tokenRepository.save(t);
            });
        }
    }

    private void saveToken(User user, String jwtToken) {
        Tokens token = Tokens.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .userName(user.getUsername())
                .build();
        tokenRepository.save(token);
    }

    public Optional<String> refreshToken(String refreshToken, HttpServletResponse response) throws IOException {
        String accessToken = null;
        if (!ObjectUtils.isEmpty(refreshToken)) {
            final var userEmail = jwtService.extractUsername(refreshToken);
            if (userEmail != null) {
                var user = userRepository.findByEmail(userEmail);
                List<Tokens> validRefreshTokens = tokenRepository.findByTokenAndUserNameAndRevoked(refreshToken, userEmail, false);
                if (user.isPresent() && validRefreshTokens.size() > 0 && jwtService.isTokenValid(refreshToken, user.get())) {
                    accessToken = jwtService.generateToken(user.get());
                    saveToken(user.get(), accessToken);
                } 
            }
        }
        return accessToken == null ? Optional.empty() : Optional.of(accessToken);
    }
}
