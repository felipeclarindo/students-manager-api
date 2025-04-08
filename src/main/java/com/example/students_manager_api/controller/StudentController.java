package com.example.students_manager_api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.students_manager_api.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private List<Student> repository = new ArrayList<>();

    @GetMapping
    public ResponseEntity<Object> getAll() {
        if (!repository.isEmpty()) {
            return ResponseEntity.ok(repository);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "No students found.");
        response.put("content", Collections.emptyList());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();

        for (Student s : repository) {
            if (s.getId().equals(student.getId())) {
                response.put("message", "Student already exists.");
                return ResponseEntity.status(409).body(response);
            }
        }

        repository.add(student);
        response.put("message", "Student created with successfully.");
        response.put("content", student);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        System.out.println("Getting student with id: " + id);

        Optional<Student> student = repository.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (student.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Student founded with successfully.");
            response.put("content", student);
            return ResponseEntity.status(200).body(response); 
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student not found.");
        return ResponseEntity.status(404).body(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Student deleted with suscessfully.");
        repository.remove(getStudent(id));
      
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = getStudent(id);
        repository.remove(existingStudent);

        student.setId(id);
        repository.add(student);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student updated with successfully.");
        response.put("content", student);

        return ResponseEntity.status(200).body(response);
    }

    private Student getStudent(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Student with id " + id + " not founded."));
    }
}
