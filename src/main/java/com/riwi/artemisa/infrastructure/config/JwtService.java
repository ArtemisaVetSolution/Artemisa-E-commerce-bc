package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final RestTemplate restTemplate;
    private final String jwtSecret;

    public JwtService(RestTemplate restTemplate, @Value("${jwt.secret}") String jwtSecret) {
        this.restTemplate = restTemplate;
        this.jwtSecret = jwtSecret;
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateTokenWithAuthService(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    "http://users-service:3001/api/auth/validate-jwt",
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );
            return Boolean.TRUE.equals(response.getBody());
        } catch (Exception e) {
            logger.error("Error validating token", e);
            return false;
        }
    }

    public Claims decodeJwt(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.error("Error decoding JWT: {}", e.getMessage());
            return null;
        }
    }

    public String extractName(Claims claims) {
        return claims.get("name", String.class);
    }

    public String extractId(Claims claims) {
        return claims.get("id", String.class);
    }

    public String extractRoleUser(Claims claims) {
        return claims.get("roleUser", String.class);
    }
}