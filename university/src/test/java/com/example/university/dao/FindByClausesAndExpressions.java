package com.example.university.dao;

import com.example.university.business.UniversityService;
import com.example.university.domain.Student;
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
    private StudentDao studentDao;

    @Test
    public void findByClausesAndExpressions() {
        // Test Create
        UniversityFactory.fillUniversity(universityService);
        List<Student> students = universityService.findAllStudents();
        Student firstStudent = students.get(0);

        assertTrue(studentDao.findOldest().get().getAge() == 22);

        assertEquals(firstStudent, studentDao.findByFirstAndLastName(firstStudent.getAttendee().getFirstName(),
                firstStudent.getAttendee().getLastName()).get(0));

        studentDao.findByAgeLessThan(20).stream().forEach(s-> assertTrue(s.getAge() < 20));

        studentDao.findSimilarLastName("%o%")
                .stream().forEach(s->assertTrue(s.getAttendee().getLastName().contains("o")));

        assertTrue(studentDao.findFirstInAlphabet().get().getAttendee().getLastName().equals("Doe"));

        List<Student> students3 = studentDao.find3Oldest();
        assertTrue(students3.size() == 3);
        students3.stream().forEach(s -> assertTrue(s.getAge() != 18));
    }
}
