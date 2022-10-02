package com.example.university.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 * Data Access Object Class for the Student Entity.
 * Uses only javax.persistence libraries.
 */
@Repository
public class StudentDao {
    EntityManager em;

    public StudentDao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }
}
