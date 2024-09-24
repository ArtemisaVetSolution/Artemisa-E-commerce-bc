package com.riwi.artemisa.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        if (!jwtService.validateTokenWithAuthService(token)) {
            throw new BadCredentialsException("Token JWT invalid");
        }

        Claims claims = jwtService.decodeJwt(token);
        String username = claims.getSubject();

        String role = null;
        if (claims.get("roles") instanceof List && !((List<?>) claims.get("roles")).isEmpty()) {
            role = ((List<?>) claims.get("roles")).get(0).toString(); // Obtener el primer rol
        } else {
            logger.warn("The 'roles' claim is empty or not a list in the JWT token");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new UsernamePasswordAuthenticationToken(username, null, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}