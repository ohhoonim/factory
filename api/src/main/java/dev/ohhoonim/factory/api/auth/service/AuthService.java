package dev.ohhoonim.factory.api.auth.service;

import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

import dev.ohhoonim.factory.api.auth.dto.AuthResponse;
import dev.ohhoonim.factory.api.config.JwtService;
import dev.ohhoonim.factory.infra.personal.auth.repository.TokenRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.UserRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Tokens;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.User;
import dev.ohhoonim.factory.infra.personal.auth.repository.type.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    // public User authenticate(User user) {
    public AuthResponse authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveToken(user, jwtToken);
        return new AuthResponse( jwtToken, refreshToken);
    }

    private void revokeAllUserTokens(User user) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserId(user.getEmail());
        if (!validTokens.isEmpty()) {
            validTokens.forEach( t-> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }
    private void saveToken (User user, String jwtToken) {
        Tokens token = Tokens.builder()
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .userName(user.getUsername())
        .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail; // username
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user= this.userRepository.findByEmail(userEmail).get();
            if (jwtService.isTokenValid(refreshToken, user)) {
               String accessToken = jwtService.generateToken(user);
               AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
