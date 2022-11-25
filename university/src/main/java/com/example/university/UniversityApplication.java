package com.example.university;


import com.example.university.domain.Department;
import com.example.university.domain.Person;
import com.example.university.domain.Staff;
import com.example.university.repo.DepartmentRepo;
import com.example.university.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * Configuration Class for the University library
 */
@SpringBootApplication
@RestController
@Transactional
public class UniversityApplication implements CommandLineRunner {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private StaffRepo staffRepo;
    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Mono<Staff> deanJonesMono = staffRepo.save(new Staff( new Person("John", "Jones")));
        Mono<Staff> deanMartinMono = staffRepo.save(new Staff(new Person("John", "Martin")));
        System.out.println("Staff count = " + staffRepo.count().block());
        Staff deanJones = deanJonesMono.block();
        Staff deanMartin = deanMartinMono.block();
        System.out.println("blocked(): Staff count = " + staffRepo.count().block());
        Flux<Department> departmentFlux = departmentRepo.saveAll(
                Arrays.asList(new Department("Humanities", deanJones),
                        new Department("Natural Sciences", deanMartin),
                        new Department("Social Sciences", deanJones)));
        departmentFlux.subscribe();
    }

    @GetMapping("/staff")
    public Flux<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    @GetMapping("/staff/{id}")
    public Mono<Staff> getStaffById(@PathVariable("id") String id) {
        return staffRepo.findById(id);
    }

    @GetMapping("/departments")
    public Flux<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @GetMapping("/departments/{id}")
    public Mono<Department> getDepartmentById(@PathVariable("id") String id) {
        return departmentRepo.findById(id);
    }

    @GetMapping("/staff/search/member/{lastName}")
    public Flux<Staff> getStaffByLastName(@PathVariable("lastName") String lastName) {
        return staffRepo.findByMemberLastName(lastName);
    }
}
