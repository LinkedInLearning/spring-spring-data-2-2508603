package com.example.university.dao;

import com.example.university.business.UniversityService;
import com.example.university.domain.Staff;
import com.example.university.repo.StaffRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Paging and Sorting Query
 */

@SpringBootTest
class PagingTest {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private UniversityService universityService;

    @Test
    void findPage() {
        UniversityFactory.fillUniversity(universityService);
        List<Staff> allStaff = universityService.findAllStaff();
        Sort alphabeticLastName = Sort.by(Sort.Order.asc("member.lastName"));
        Page<Staff> pageObject = staffRepo.findAll(PageRequest.of(1, 5, alphabeticLastName));
        List<Staff> staffPage = pageObject.getContent();
        assertTrue(staffPage.get(0).getMember().getLastName().compareTo(staffPage.get(1).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(1).getMember().getLastName().compareTo(staffPage.get(2).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(2).getMember().getLastName().compareTo(staffPage.get(3).getMember().getLastName()) < 0);
        assertTrue(staffPage.get(3).getMember().getLastName().compareTo(staffPage.get(4).getMember().getLastName()) < 0);
    }
}