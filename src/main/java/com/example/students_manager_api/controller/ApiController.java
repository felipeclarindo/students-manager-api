package com.example.students_manager_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping
    public ResponseEntity<Object> index() {
        Map<String, String> response = new HashMap<>();
        
        response.put("message", "Welcome to the Students Manager API");
        response.put("status", "API is running");
        response.put("description", "This API is a simple CRUD to manage students");
        response.put("author", "Felipe Clarindo");
        response.put("author-github", "https://github.com/felipeclarindo");
        response.put("version", "1.0.0");
        response.put("repository", "https://github.com/felipeclarindo/students-manager-api");

        return ResponseEntity.status(200).body(response);
    }
}