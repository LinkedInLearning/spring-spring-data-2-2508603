package com.example.university.dao;

import com.example.university.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Data Access Object Class for the Course Entity.
 * Uses only javax.persistence libraries.
 */
@Repository
public class CourseDao {
    private EntityManager em;

    public CourseDao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    //Dynamic Query by various attributes
    public List<Course> findByCriteria(CriteriaQuery<Course> criteria){
        return em.createQuery(criteria).getResultList();
    }
}
