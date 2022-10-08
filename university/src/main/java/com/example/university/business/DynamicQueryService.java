package com.example.university.business;

import com.example.university.domain.Course;
import com.example.university.repo.CourseRepo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public List<Course> findByExample(CourseFilter filter){
        Course course = new Course(null,
                filter.getCredits().orElse(null),
                filter.getInstructor().orElse(null),
                filter.getDepartment().orElse(null));
        return courseRepo.findAll(Example.of(course));
    }


}
