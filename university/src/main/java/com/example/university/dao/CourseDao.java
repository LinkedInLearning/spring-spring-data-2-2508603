package com.example.university.dao;

import com.example.university.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

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

    public Optional<Course> findByName(String name) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE c.name = :name", Course.class);
        return Optional.ofNullable(query.setParameter("name", name).getSingleResult());
    }

    public List<Course> findByChairLastName(String chair) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE c.department.chair.member.lastName = :chair", Course.class);
        return query.setParameter("chair", chair).getResultList();
    }

    public List<Course> findCourseByPrerequisite(int id) {
        TypedQuery<Course> query = em.createQuery(
                "Select c from Course c join c.prerequisites p where p.id = ?1", Course.class);
        return query.setParameter(1, id).getResultList();
    }

    public List<Course> findByCredits(int credits) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE c.credits = :credits", Course.class);
        return query.setParameter("credits", credits).getResultList();
    }

    public List<Course> findByCriteria(CriteriaQuery<Course> criteria){
        return em.createQuery(criteria).getResultList();
    }
}
