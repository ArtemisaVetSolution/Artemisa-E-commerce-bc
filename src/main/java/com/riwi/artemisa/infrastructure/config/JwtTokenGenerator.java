package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ... (otras importaciones)

public class JwtTokenGenerator {

    public static String generateJwtToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "admin_user"); // Nombre de usuario o ID del administrador
        claims.put("roles", List.of("ADMIN")); // Asegúrate de usar una lista de strings
        claims.put("id", "f7d497f6-2c4c-4981-9dbc-c6d7b7b21c42"); // ID del administrador (puedes usar cualquier valor)

        String secretKey = "123Artemisa45689daissdjhf@$";

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 360000000); // 1 hora de expiración

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}