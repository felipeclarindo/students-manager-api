package com.example.students_manager_api.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students_manager_api.auth.model.Credentials;
import com.example.students_manager_api.auth.model.Token;
import com.example.students_manager_api.auth.model.User;
import com.example.students_manager_api.auth.service.AuthService;
import com.example.students_manager_api.auth.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {
        var user = authService.loadUserByUsername(credentials.email());
        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        return tokenService.createToken((User) user);
    }

}
