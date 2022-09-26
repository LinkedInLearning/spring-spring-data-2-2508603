package com.example.university.dao;

import com.example.university.PersistenceJPAConfig;
import com.example.university.business.UniversityService;
import com.example.university.domain.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that verify simple CRUD methods
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class })
public class SimpleDBCrudTest {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StaffDao staffDao;

    private List<Staff> allStaff;
    private Optional<Staff> oneStaff;
    private int id;

    @Test
    public void testStaffCrud() {
        // Test Create
        UniversityFactory.fillUniversity(universityService);
        //Test FindA ll
        List<Staff> allStaff = universityService.findAllStaff();
        int totalStaff = allStaff.size();
        allStaff.stream().forEach(System.out::println);
        assertEquals(11,  allStaff.size());

        // Test Find by Id
        Staff deanThomas = allStaff.get(0);
        System.out.println(deanThomas);
        assertEquals(deanThomas, staffDao.findById(deanThomas.getId()).get());

        // Test Update, Change first Name to Patrick
        deanThomas.getMember().setFirstName("Patrick");
        staffDao.save(deanThomas);

        assertEquals("Patrick",
                staffDao.findById(deanThomas.getId()).get().getMember().getFirstName());

        staffDao.delete(deanThomas);
        allStaff = staffDao.findAll();
        assertEquals(totalStaff -1, allStaff.size());
        allStaff.stream().forEach(System.out::println);
    }
}
