package com.example.university.repo;

import com.example.university.UniversityApplication;
import com.example.university.domain.Department;
import com.example.university.domain.Person;
import com.example.university.domain.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Staff deanJones = createStaff("John", "Jones");
        Staff deanMartin = createStaff("Matthew", "Martin");
        Staff profBrown = createStaff("James", "Brown");
        Staff profMiller = createStaff("Judy", "Miller");
        Staff profDavis = createStaff("James", "Davis");
        Staff profMoore = createStaff("Allison", "Moore");
        Staff profThomas = createStaff("Tom", "Thomas");
        Staff profGreen = createStaff("Graham", "Green");
        Staff profWhite = createStaff("Whitney", "White");
        Staff profBlack = createStaff("Jack", "Black");
        Staff profKing = createStaff("Queen", "King");
        //Departments
        Department humanities = createDepartment("Humanities", deanJones);
        Department naturalSciences = createDepartment("Natural Sciences", deanMartin);
        Department socialSciences = createDepartment( "Social Sciences", deanJones);


        //Paging and Sorting Queries
        System.out.println("\nFind all staff members, sort alphabetically by last name");
        Sort sortByLastName = Sort.by(Sort.Direction.ASC, "member.lastName");
        staffRepo.findAll(sortByLastName).forEach(System.out::println);

        System.out.println("\nFind first 5 Staff members, sort alphabetically by last name");
        Page<Staff> members = staffRepo.findAll(PageRequest.of(0, 5, sortByLastName));
        members.forEach(System.out::println);


        //Property Expression
        System.out.println("\nFind all staff members with last name King");
        staffRepo.findByMemberLastName("King").forEach(System.out::println);

        //@Query with JSON query string
        //"{ 'member.firstName' : ?0 }"
        System.out.println("\nFind all staff members with first name John");
        staffRepo.findByFirstName("John").forEach(System.out::println);


        //***************Department query methods***************

        //Sorting example, MongoRepository extends PagingAndSortingRepository
        System.out.println("\nFind all Departments, sort alphabetically by  name");
        departmentRepo.findAll(Sort.by(Sort.Direction.ASC, "name")).forEach(System.out::println);

        //Property Expression
        System.out.println("\nFind Department with the exact name 'Humanities' \n" +
                departmentRepo.findByName("Humanities"));

        //@Query with JSON query string that accepts regular expression as a parameter
        //{ 'name' : { $regex: ?0 } }
        //Any department name that ends in sciences where 's' is case insensitive
        System.out.println("\nFind all Departments with name ending in Sciences");
        departmentRepo.findNameByPattern(".[Ss]ciences").forEach(System.out::println);

        System.out.println("\nFind Departments chaired by Dean Jones");
        departmentRepo.findByChairId(deanJones.getId()).forEach(System.out::println);
    }

    public Staff createStaff(String firstName, String lastName) {
        return staffRepo.save(new Staff(new Person(firstName, lastName)));
    }
    public Department createDepartment(String deptname, Staff deptChair) {
        return departmentRepo.findByName(deptname)
                .orElse(departmentRepo.save(new Department(deptname, deptChair)));
    }

    private void fillUniversity() {
        //Staff


    }
}
