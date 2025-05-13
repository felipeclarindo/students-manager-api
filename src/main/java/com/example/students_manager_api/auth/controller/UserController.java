package com.example.students_manager_api.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students_manager_api.auth.model.User;
import com.example.students_manager_api.auth.model.UserRole;
import com.example.students_manager_api.auth.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return repository.save(user);
    }

}
