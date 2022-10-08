package com.example.university.business;

import com.example.university.domain.Course;
import com.example.university.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    public DynamicQueryService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> filterBySpecification(CourseFilter filter) {
        return courseRepo.findAll(filter.getSpecification());
    }



}
