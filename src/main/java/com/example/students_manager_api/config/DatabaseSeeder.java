package com.example.students_manager_api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.students_manager_api.model.Student;
import com.example.students_manager_api.repository.StudentRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    public void init() {
        var names = List.of(
                "Felipe Clarindo", "Maria Silva", "João Oliveira", "Ana Souza", "Carlos Santos",
                "Larissa Costa", "Pedro Martins", "Juliana Rocha", "Bruno Lima", "Amanda Teixeira");

        var courses = List.of("Engenharia", "Ciência da Computação", "Administração", "Direito", "Medicina");

        var periods = List.of("Matutino", "Vespertino", "Noturno");

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 59; i++) {
            List<Double> notes = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                notes.add(getRandomGrade());
            }

            students.add(
                    Student.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .course(courses.get(random.nextInt(courses.size())))
                            .period(periods.get(random.nextInt(periods.size())))
                            .notes(notes)
                            .build());
        }

        studentRepository.saveAll(students);
    }

    private Double getRandomGrade() {
        return Math.round(random.nextDouble() * 10 * 10.0) / 10.0;
    }
}