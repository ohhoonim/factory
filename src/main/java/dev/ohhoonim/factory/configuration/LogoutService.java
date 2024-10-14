package dev.ohhoonim.factory.configuration;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import dev.ohhoonim.factory.personalRepository.TokenRepository;
import dev.ohhoonim.factory.personalTable.Tokens;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        token = authHeader.substring(7);
        if (!StringUtils.isEmpty(token) && !"undefined".equals(token)) {
            final String userEmail = jwtService.extractUsername(token);
            revokeAllUserTokens(userEmail);
        }
    }

    private void revokeAllUserTokens(String username) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserId(username);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }

}
