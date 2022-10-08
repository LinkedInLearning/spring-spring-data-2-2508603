package com.example.university.repo;

import com.example.university.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CourseQueryDslRepo extends JpaRepository<Course, Integer>, QuerydslPredicateExecutor<Course> {
}
