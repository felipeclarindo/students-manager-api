package com.example.students_manager_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students_manager_api.model.ApiResponseDescription;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity<Object> index() {
        ApiResponseDescription apiDescription = new ApiResponseDescription();

        apiDescription.setMessage("Welcome to the Students Manager API").setStatus("API is running")
                .setDescription("This API is a simple CRUD to manage students.").setAuthor("Felipe Clarindo")
                .setGithubAuthor("https://github.com/felipeclarindo").setVersion("1.0.0")
                .setRepositoryUrl("https://github.com/felipeclarindo/students-manager-api");

        return ResponseEntity.status(200).body(apiDescription);
    }
}