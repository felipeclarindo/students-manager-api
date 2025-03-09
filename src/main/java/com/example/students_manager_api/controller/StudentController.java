package com.example.students_manager_api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.students_manager_api.model.Student;

@RestController
public class StudentController {
    
    private List<Student> repository = new ArrayList<>();

    @GetMapping("/students")
    public ResponseEntity<Object> getAll() {
        Map<String, Object> response = new HashMap<>();

        if (!repository.isEmpty()) {
            response.put("content", repository);
            response.put("message", "Students found susccessfully");
            return ResponseEntity.status(200).body(response);
        }
        response.put("message", "No students found");
        return ResponseEntity.status(204).body(response);
    }

    @PostMapping("/students") 
    public ResponseEntity<Object> create(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();

        repository.add(student);
        response.put("content", student);
        response.put("message", "Student created successfully");
        return ResponseEntity.status(201).body(response);
}

    @GetMapping("/students/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        System.out.println("Getting category with id: " + id);

        var student = repository.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst();
        if (student.isEmpty()) {
            response.put("message", "Student not found");
            return ResponseEntity.status(204).body(response);
        }
        return ResponseEntity.status(201).body(response);
    }
}