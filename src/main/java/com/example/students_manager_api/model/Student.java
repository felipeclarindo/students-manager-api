package com.example.students_manager_api.model;

import java.util.List;

public class Student {
    private Long id;
    private String name;
    private String course;
    private String period;
    private List<Number> notes;

    public Student(Long id, String name, String course, String period, List<Number> notes) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.period = period;
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

    public String getCourse() {
        return course;
    }

    public List<Number> getNotes() {
        return notes;
    }

    public String getPeriod() {
        return period;
    }
}
