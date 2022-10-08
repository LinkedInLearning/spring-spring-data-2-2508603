package com.example.university.business;

import com.example.university.domain.Department;
import com.example.university.domain.Staff;

import java.util.Optional;

/**
 * Helper class to filter courses in the Dynamic Query Service
 */
public class CourseFilter {
    private Optional<Department> department = Optional.empty();
    private Optional<Integer> credits = Optional.empty();
    private Optional<Staff> instructor = Optional.empty();

    public static CourseFilter filterBy(){
        return new CourseFilter();
    }

    public CourseFilter department(Department department) {
        this.department = Optional.of(department);
        return this;
    }
    public CourseFilter credits(Integer credits) {
        this.credits = Optional.of(credits);
        return this;
    }

    public CourseFilter instructor(Staff instructor) {
        this.instructor = Optional.of(instructor);
        return this;
    }

    public Optional<Department> getDepartment() {
        return department;
    }

    public Optional<Integer> getCredits() {
        return credits;
    }

    public Optional<Staff> getInstructor() {
        return instructor;
    }
    /*
       public Specification<Course> getSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            department.ifPresent(d ->
                    predicates.add(criteriaBuilder.equal(root.get("department"), d)));
            credits.ifPresent(c ->
                    predicates.add(criteriaBuilder.equal(root.get("credits"), c)));
            instructor.ifPresent(i ->
                    predicates.add(criteriaBuilder.equal(root.get("instructor"), i)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
     */
}
