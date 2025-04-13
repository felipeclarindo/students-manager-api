package com.example.students_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.students_manager_api.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
