package com.example.university.repo;

import com.example.university.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByAge(Integer age);
    List<Student> findByAttendeeLastName(String lastName);

    /*
    //findOldest
     Optional<Student> findTopByOrderByAgeDesc();

    //findByFirstAndLastName
     List<Student> findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

    //findByAgeLessThan
     List<Student> findByAgeLessThan(int age) ;

    //findSimilarLastName
     List<Student> findByAttendeeLastNameLike(String nameCriteria);

    //findFirstInAlphabet
     Optional<Student> findFirstByOrderByAttendeeLastNameAsc();

    //find3Oldest
     List<Student> findTop3ByOrderByAgeDesc() ;
     */
}
