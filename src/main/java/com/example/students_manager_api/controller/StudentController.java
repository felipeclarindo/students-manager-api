package com.example.students_manager_api.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import com.example.students_manager_api.model.StudentFilter;
import com.example.students_manager_api.repository.StudentRepository;
import com.example.students_manager_api.specification.StudentEspecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    @Cacheable(value = "students")
    @Operation(summary = "List all students", description = "List all students saved.", tags = { "Students" })
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a student by ID", description = "Get a student based on the provided ID.", tags = {
            "Students" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Student found"),
                    @ApiResponse(responseCode = "404", description = "Student not found") })
    public Student get(@PathVariable Long id) {
        log.info("Getting student with id: {}", id);
        return getStudent(id);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter students", description = "Filter students based on the provided filters.", tags = {
            "Students" }, responses = @ApiResponse(responseCode = "200", description = "Filtered students"))
    public Page<Student> index(
            @ParameterObject StudentFilter filter,
            @PageableDefault(size = 10, direction = Direction.DESC) Pageable pageable) {
        return repository.findAll(StudentEspecification.withFilters(filter), pageable);
    }

    @PostMapping
    @CacheEvict(value = "students", allEntries = true)
    @Operation(responses = @ApiResponse(responseCode = "201", description = "Student created successfully"), requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student object to create"), tags = {
            "Students" })
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody @Valid Student student) {
        log.info("Creating student: {}", student.getName());
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "students", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a student by ID", description = "Deletes a student based on the provided ID.", tags = {
            "Students" })
    public void destroy(@PathVariable Long id) {
        log.info("Deleting student with id: {}", id);
        repository.delete(getStudent(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "students", allEntries = true)
    @Operation(summary = "Update a student by ID", description = "Updates an existing student based on the provided ID.", tags = {
            "Students" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found") }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student object to update"))
    public Student update(@PathVariable Long id, @RequestBody @Valid Student student) {
        log.info("Updating student with id: {}", id);
        getStudent(id);
        student.setId(id);
        return repository.save(student);
    }

    private Student getStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student with id " + id + " not found."));
    }
}
