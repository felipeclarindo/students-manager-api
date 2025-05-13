package com.example.students_manager_api.auth.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.students_manager_api.auth.model.Token;
import com.example.students_manager_api.auth.model.User;
import com.example.students_manager_api.auth.repository.UserRepository;

@Service
public class TokenService {

    @Autowired
    UserRepository userRepository;

    private Instant experiesAt = LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret-muito-secreto-que-ninguem-pode-saber");

    public Token createToken(User user) {
        var jwt = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().toString())
                .withExpiresAt(experiesAt)
                .sign(algorithm);

        return new Token(jwt, "Bearer", user.getEmail());
    }

    public UserDetails getUserFromToken(String jwt) {
        var jwtVerified = JWT.require(algorithm).build().verify(jwt);
        var email = jwtVerified.getClaim("email").asString();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
