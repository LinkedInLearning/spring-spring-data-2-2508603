package com.example.university.dao;

import com.example.university.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Optional;
/**
 * Data Access Object Class for the Department Entity.
 * Uses only javax.persistence libraries.
 */

@Repository
public class DepartmentDao {

    private EntityManager em;
    public DepartmentDao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    //Query By Simple Attribute
    public Optional<Department> findByName(String name) {
        TypedQuery<Department> query = em.createQuery(
                "SELECT d FROM Department d WHERE d.name = :name", Department.class);
        return Optional.ofNullable(query.setParameter("name", name).getSingleResult());
    }
}
