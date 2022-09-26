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

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Paging and Sorting Query
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class })
class PagingTest {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private UniversityService universityService;

    @Test
    void findPage() {
        UniversityFactory.fillUniversity(universityService);
        List<Staff> allStaff = universityService.findAllStaff();
        Staff firstStaff = allStaff.get(0);
        List<Staff> staffPage = staffDao.find(1, 5);
        assertTrue(staffPage.get(0).getMember().getLastName().compareTo(staffPage.get(1).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(1).getMember().getLastName().compareTo(staffPage.get(2).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(2).getMember().getLastName().compareTo(staffPage.get(3).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(3).getMember().getLastName().compareTo(staffPage.get(4).getMember().getLastName()) < 0);
    }
}