package com.example.university.repo;

import com.example.university.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

 public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByAge(Integer age);
    List<Student> findByAttendeeLastName(String lastName);

    //Queries with clauses and expressions
     Optional<Student> findTopByOrderByAgeDesc();

     List<Student> findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

     List<Student> findByAgeLessThan(int age) ;

     List<Student> findByAttendeeLastNameLike(String nameCriteria);

     Optional<Student> findFirstByOrderByAttendeeLastNameAsc();

     List<Student> findTop3ByOrderByAgeDesc() ;

     /**
      * Copy/paste bank for Native queries
      findOldest() naitve
      SELECT * FROM student s ORDER BY s.age DESC LIMIT 1

      findFirstInAlphabet
      SELECT * FROM STUDENT s ORDER BY s.Last_Name ASC LIMIT

      find3Oldest
      SELECT * FROM STUDENT s ORDER BY s.age DESC LIMIT 3
      **/
 }
