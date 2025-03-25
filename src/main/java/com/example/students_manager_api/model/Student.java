package com.example.students_manager_api.model;

import java.util.List;

public class Student {
    private Long id;
    private String name;
    private List<Number> notes;

    public Student(Long id, String name, List<Number> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public List<Number> getNotes() {
        return notes;
    }
}
