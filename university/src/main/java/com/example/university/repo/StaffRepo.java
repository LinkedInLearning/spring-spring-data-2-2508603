package com.example.university.repo;

import com.example.university.domain.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path="staff", collectionResourceRel="staff")
public interface StaffRepo extends JpaRepository<Staff, Integer> {

    @Query("SELECT s FROM Staff s WHERE s.member.lastName = :lastName")
    List<Staff> findByLastName(@Param("lastName") String lastName, Pageable pageable);
}
