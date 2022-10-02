package com.example.university.repo;

import com.example.university.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

    @Query("SELECT s FROM Staff s WHERE s.member.lastName = :lastName")
    List<Staff> findByLastName(@Param("lastName") String lastName);
}
