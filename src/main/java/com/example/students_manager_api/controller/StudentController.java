package com.example.students_manager_api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.students_manager_api.model.Student;

@RestController
public class StudentController {
    
    private List<Student> repository = new ArrayList<>();

    @GetMapping("/api/students")
    public ResponseEntity<Object> getAll() {
        if (!repository.isEmpty()) {
            Map<String, Object> response = new HashMap<>();

            response.put("content", repository);
            response.put("message", "Students successfully found");
            return ResponseEntity.status(200).body(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "No students found");
        response.put("content", Collections.emptyList());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/api/students") 
    public ResponseEntity<Object> create(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();

        for (Student s : repository) {
            if (s.getId().equals(student.getId())) {
                response.put("message", "Student already exists");
                return ResponseEntity.status(409).body(response);
            }
        }

        repository.add(student);
        response.put("message", "Student successfully created");
        response.put("content", student);
        return ResponseEntity.status(201).body(response);
}

    @GetMapping("/api/students/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        System.out.println("Getting student with id: " + id);
            
        Optional<Student> student = repository.stream()
                    .filter(s -> s.getId().equals(id))
                    .findFirst();

        if (student.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Student successfully found");
            response.put("content", student);
            return ResponseEntity.status(200).body(response); 
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student not found");
        return ResponseEntity.status(404).body(response);
    }

}