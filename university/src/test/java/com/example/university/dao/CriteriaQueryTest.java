package com.example.university.dao;

import com.example.university.PersistenceJPAConfig;
import com.example.university.business.DynamicQueryService;
import com.example.university.business.UniversityService;
import com.example.university.domain.Department;
import com.example.university.domain.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Test Criteria-based queries
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class })
public class CriteriaQueryTest {

    @Autowired
    private DynamicQueryService queryService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private StaffDao staffDao;

    @Test
    void findByCriteria() {
        UniversityFactory.fillUniversity(universityService);
        Optional<Department> humanities = departmentDao.findByName("Humanities");
        Optional<Staff> black = staffDao.findByLastName("Black").stream().findFirst();

        System.out.println('\n' + "*** All Humanities Courses");
        queryService.findCoursesByCriteria(humanities, empty(), empty())
                .stream().forEach(System.out::println);

        System.out.println('\n' + "*** 4 credit courses");
        queryService.findCoursesByCriteria(empty(), of(4), empty())
                .stream().forEach(System.out::println);

        System.out.println('\n' + "*** Courses taught by Professor Black");
        queryService.findCoursesByCriteria(empty(), empty(), black)
                .stream().forEach(System.out::println);

        System.out.println('\n' + "*** Courses In Humanties, taught by Professor Black, 4 credits");
        queryService.findCoursesByCriteria(humanities, of(4), black)
                .stream().forEach(System.out::println);
    }
}
