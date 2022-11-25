package com.example.university.repo;

import com.example.university.domain.Staff;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * DataSource Management for the Staff at the University.
 * <p>
 * Created by maryellenbowman.
 */
public interface StaffRepo extends ReactiveCrudRepository<Staff, String> {

    Flux<Staff> findByMemberLastName(String lastName);

}
