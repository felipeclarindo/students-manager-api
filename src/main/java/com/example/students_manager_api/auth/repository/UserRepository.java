package com.example.students_manager_api.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.students_manager_api.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

}
