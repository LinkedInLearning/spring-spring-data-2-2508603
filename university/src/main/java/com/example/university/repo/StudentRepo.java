package com.example.university.repo;

import com.example.university.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByAge(Integer age);
    List<Student> findByAttendeeLastName(String lastName);


    /* copy/paste bank
    findTopByOrderByAgeDesc
    findByAttendeeFirstNameAndAttendeeLastName
    findByAgeLessThan
    findByAttendeeLastNameLike
    findFirstByOrderByAttendeeLastNameAsc
    List<Student> findTop3ByOrderByAgeDesc
     */
}
