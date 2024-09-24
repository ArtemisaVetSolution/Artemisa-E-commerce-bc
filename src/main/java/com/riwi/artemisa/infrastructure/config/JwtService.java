package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired // Inyecta RestTemplate en el constructor
    public JwtService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${jwt.secret}") // Inyecta el secreto JWT desde la configuración
    private String jwtSecret;

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // validar el token desde la solicitud en node
    public boolean validateTokenWithAuthService(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    "http://users-service:3001/api/auth/validate-jwt",
                    HttpMethod.POST,
                    entity,
                    Boolean.class
            );

            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            logger.error("Error validating token", e);
            return false;
        }
    }

    //validar el token loclamente
//    public boolean validateTokenWithAuthService(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true; // El token es válido
//        } catch (Exception e) {
//            logger.error("Error validating token locally", e);
//            return false; // El token es inválido o ha expirado
//        }
//    }

    public Claims decodeJwt(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            logger.error("JWT signature invalid: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT malformed: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT expiration: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT not supported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims void: {}", e.getMessage());
        }
        return null;
    }
}