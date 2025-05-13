package com.example.students_manager_api.auth.model;

public record Token(
        String token,
        String type,
        String email
        ) {

}
