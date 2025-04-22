package com.example.students_manager_api.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is needed.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @NotBlank(message = "Course is needed.")
    @Size(min = 3, max = 50, message = "Course must be between 3 and 50 characters.")
    private String course;

    @NotBlank(message = "Period is needed.")
    @Size(min = 3, max = 50, message = "Period must be between 3 and 50 characters.")
    private String period;

    @ElementCollection
    @Column(name = "note", columnDefinition = "DOUBLE")
    @CollectionTable(name = "student_notes", joinColumns = @JoinColumn(name = "student_id"))
    private List<Double> notes;
}
