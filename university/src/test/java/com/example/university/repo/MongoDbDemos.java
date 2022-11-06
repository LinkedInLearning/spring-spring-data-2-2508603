package com.example.university.repo;

import com.example.university.UniversityApplication;
import com.example.university.domain.Department;
import com.example.university.domain.Person;
import com.example.university.domain.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * Demonstrate Various Querying Techniques with Spring Data MongoDb
 *
 * Created by maryellenbowman
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MongoDbDemos {

	@Autowired
    StaffRepo staffRepo;

	@Autowired
    DepartmentRepo departmentRepo;

     /**
     * Queries to Mongo DB.
     *
     * Staff and Departments persisted to Fongo in-Memory database at startup.
     * @see UniversityApplication
     */
	@Test
	public void mongoQueryMethods() {
        //Create 2 Mono Staff publishers, data not persisted yet!
        Mono<Staff> deanJonesMono = staffRepo.save(new Staff( new Person("John", "Jones")));
        Mono<Staff> deanMartinMono = staffRepo.save(new Staff(new Person("John", "Martin")));

        Flux<Staff> staffFlux = staffRepo.findAll();

        System.out.println("Staff count found in DB BEFORE subscription: " + staffFlux.count().block());

        //Subscribe with block(), returns entity
        Staff deanJones = deanJonesMono.block();

        //Subscribe with subscribe(), does not return entity
        deanMartinMono.subscribe();

        System.out.println("Staff count found in DB AFTER subscription:" + staffFlux.count().block());

        //Query returns a Flux publisher
        Flux<Staff> deanMartinFindByFlux = staffRepo.findByMemberLastName("Martin");
        Staff deanMartin = deanMartinFindByFlux.blockFirst();

        //Departments

        //Create an asynchronous publisher that gets the total number of Departments
        Mono<Long> departmentCountMono = departmentRepo.findAll().count();

        //Create a asynchronouse Flux publisher that persists 3 new Departments
        Flux<Department> departmentFlux = departmentRepo.saveAll(
                Arrays.asList(new Department("Humanities", deanJones),
                        new Department("Natural Sciences", deanMartin),
                        new Department("Social Sciences", deanJones)));

        System.out.println("Departments found in DB BEFORE subscription: "
                + departmentCountMono.block());

        //Persist the 3 departments, and block until complete
        departmentFlux.blockLast();

        System.out.println(("Departments found in DB AFTEr subscription: "
                + departmentCountMono.block()));
    }

}
