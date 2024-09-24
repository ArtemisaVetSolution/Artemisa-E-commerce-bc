package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.security.MessageDigest;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final RestTemplate restTemplate;
    private final SecretKey secretKey;

    
    public JwtService(@Value("${jwt.secret}") String secret, RestTemplate restTemplate) {
    this.secretKey = Keys.hmacShaKeyFor(deriveKey(secret));
    this.restTemplate = restTemplate;
}

private byte[] deriveKey(String secret) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return Arrays.copyOf(digest.digest(secret.getBytes(StandardCharsets.UTF_8)), 32);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("SHA-256 algorithm not found", e);
    }
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
            ResponseEntity<JwtValidationResponse> response = restTemplate.exchange(
                    "http://users-service:3001/api/auth/validate-jwt",
                    HttpMethod.GET,
                    entity,
                    JwtValidationResponse.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK) {
                JwtValidationResponse validationResponse = response.getBody();
                if (validationResponse != null && validationResponse.getData() != null) {
                    return validationResponse.getData().isValid();
                } else {
                    logger.warn("Validation response or its data is null");
                    return false;
                }
            } else {
                logger.warn("Unexpected response from auth service: " + response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            logger.error("Error validating token", e);
            return false;
        }
    }

    public Claims decodeJwt(String token) {
        try {
            logger.info("Decoding JWT with secret: {}", new String(secretKey.getEncoded())); // Agrega este log para verificar la clave secreta
            return Jwts.parserBuilder()
                       .setSigningKey(secretKey) // Usar la clave secreta ya inicializada
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
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