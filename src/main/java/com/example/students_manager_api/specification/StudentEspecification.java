package com.example.students_manager_api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.students_manager_api.model.Student;
import com.example.students_manager_api.model.StudentFilter;

import jakarta.persistence.criteria.Predicate;

public class StudentEspecification {

    public static Specification<Student> withFilters(StudentFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.course() != null) {
                predicates.add(
                        cb.like(cb.lower(root.get("course")), "%" + filter.course().toLowerCase() + "%"));
            }

            if (filter.period() != null) {
                predicates.add(
                        cb.like(cb.lower(root.get("period")), "%" + filter.period().toLowerCase() + "%"));
            }

            if (filter.name() != null) {
                predicates.add(
                        cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
