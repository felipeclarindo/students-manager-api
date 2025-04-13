package com.example.students_manager_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.students_manager_api.model.Student;
import com.example.students_manager_api.repository.StudentRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void init() {
        var categories = List.of(
                Student.builder().name("Felipe").course("Data Science").period("Morning").notes(List.of()).build(),
                Student.builder().name("Lucas").course("Data Science").period("Morning").notes(List.of()).build(),
                Student.builder().name("Ana").course("Data Science").period("Morning").notes(List.of()).build());
        studentRepository.saveAll(categories);
    }
}
