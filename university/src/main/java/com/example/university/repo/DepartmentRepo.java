package com.example.university.repo;

import com.example.university.domain.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * DataSource Management for the Departments at the University.
 * <p>
 * Created by maryellenbowman.
 */
public interface DepartmentRepo extends MongoRepository<Department, String> {

    Optional<Department> findByName(String name);

    List<Department> findByChairId(String chairId);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<Department> findNameByPattern(String pattern);


}
