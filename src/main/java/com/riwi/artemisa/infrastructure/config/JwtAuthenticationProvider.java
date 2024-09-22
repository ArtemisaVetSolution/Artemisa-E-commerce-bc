package com.riwi.artemisa.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        if (!jwtService.validateTokenWithAuthService(token)) {
            throw new BadCredentialsException("Token JWT inválido");
        }

        Claims claims = jwtService.decodeJwt(token);
        String username = claims.getSubject();
        List<String> roles = (List<String>) claims.get("roles");

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}