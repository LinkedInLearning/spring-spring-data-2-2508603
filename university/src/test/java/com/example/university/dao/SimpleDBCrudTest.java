package com.example.university.dao;

import com.example.university.business.UniversityService;
import com.example.university.domain.Staff;
import com.example.university.repo.StaffRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that verify simple CRUD methods
 */

@SpringBootTest
public class SimpleDBCrudTest {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StaffRepo staffRepo;

    private List<Staff> allStaff;
    private Optional<Staff> oneStaff;
    private int id;

    @Test
    public void testStaffCrud() {
        // Test Create
        UniversityFactory.fillUniversity(universityService);
        //Test FindA ll
        List<Staff> allStaff = staffRepo.findAll();
        int totalStaff = allStaff.size();
        allStaff.stream().forEach(System.out::println);
        assertEquals(11,  allStaff.size());

        // Test Find by Id
        Staff deanThomas = allStaff.get(0);
        System.out.println(deanThomas);
        assertEquals(deanThomas, staffRepo.findById(deanThomas.getId()).get());

        // Test Update, Change first Name to Patrick
        deanThomas.getMember().setFirstName("Patrick");
        staffRepo.save(deanThomas);

        assertEquals("Patrick",
                staffRepo.findById(deanThomas.getId()).get().getMember().getFirstName());

        staffRepo.delete(deanThomas);
        allStaff = staffRepo.findAll();
        assertEquals(totalStaff -1, allStaff.size());
        allStaff.stream().forEach(System.out::println);
    }
}
