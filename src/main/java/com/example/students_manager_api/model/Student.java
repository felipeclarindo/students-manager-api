package com.example.students_manager_api.model;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private Date dateOfBirth;

    public Student(Long id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfbirth() {
        return dateOfBirth;
    }
}
