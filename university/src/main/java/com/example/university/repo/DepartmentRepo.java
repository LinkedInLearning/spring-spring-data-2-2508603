package com.example.university.repo;

import com.example.university.domain.Department;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * DataSource Management for the Departments at the University.
 * <p>
 * Created by maryellenbowman.
 */
public interface DepartmentRepo extends ReactiveCrudRepository<Department, String> {
    Mono<Department> findByName(String name);

    Flux<Department> findByChairId(String chairId);

    @Query("{ 'name' : { $regex: ?0 } }")
    Flux<Department> findNameByPattern(String pattern);
}
