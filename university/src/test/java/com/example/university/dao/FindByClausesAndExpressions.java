package com.example.university.dao;

import com.example.university.business.UniversityService;
import com.example.university.domain.Student;
import com.example.university.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test queries that involve clauses and expressions
 */

@SpringBootTest
public class FindByClausesAndExpressions {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void findByClausesAndExpressions() {
        // Test Create
        UniversityFactory.fillUniversity(universityService);
        List<Student> students = universityService.findAllStudents();
        Student firstStudent = students.get(0);

        assertTrue(studentRepo.findOldest().get().getAge() == 22);

        assertEquals(firstStudent, studentRepo.findByFirstAndLastName(firstStudent.getAttendee().getFirstName(),
                firstStudent.getAttendee().getLastName()).get(0));

        studentRepo.findByAgeLessThan(20).stream().forEach(s-> assertTrue(s.getAge() < 20));

        studentRepo.findBySimilarLastName("%o%")
                .stream().forEach(s->assertTrue(s.getAttendee().getLastName().contains("o")));

        assertTrue(studentRepo.findFirstInAlphabet().get().getAttendee().getLastName().equals("Doe"));

        List<Student> students3 = studentRepo.find3Oldest();
        assertTrue(students3.size() == 3);
        students3.stream().forEach(s -> assertTrue(s.getAge() != 18));
    }
}
