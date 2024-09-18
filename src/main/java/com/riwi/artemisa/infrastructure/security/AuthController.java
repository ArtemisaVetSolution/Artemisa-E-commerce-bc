package com.riwi.artemisa.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login() {
        return null;
    }

    @PostMapping(value = "Register")
    public String Register() {
        return null;
    }
}
