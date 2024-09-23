package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.riwi.artemisa.infrastructure.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@WebFilter(urlPatterns = "/*")
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Declaración del servicio

    // Constructor para inyectar JwtService
    public JwtRequestFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = jwtService.extractJwtFromRequest(request);

        if (jwtToken != null && jwtService.validateTokenWithAuthService(jwtToken)) {
            Claims claims = jwtService.decodeJwt(jwtToken);
            String username = claims.getSubject();

            String role = null;
            if (claims.get("roles") instanceof List && !((List<?>) claims.get("roles")).isEmpty()) {
                role = ((List<?>) claims.get("roles")).get(0).toString();
            } else {
                logger.warn("The 'roles' claim is empty or not a list in the JWT token");
            }

            String userId = (String) claims.get("id");

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (role != null) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                // Agregar el prefijo "ROLE_"
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            authenticationToken.setDetails(userId);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
