package com.example.university.dao;

import com.example.university.domain.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
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

    //Queries with clauses and expressions
    public Optional<Student> findOldest() {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s ORDER BY age DESC" , Student.class);
        return Optional.ofNullable(query.setMaxResults(1).getSingleResult());
    }

    public List<Student> findByFirstAndLastName(String firstName, String lastName){
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE s.attendee.firstName = :firstName and s.attendee.lastName = :lastName" , Student.class);
        return query.setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<Student> findByAgeLessThan(int age) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s where s.age < :age" , Student.class);
        return query.setParameter("age", age)
                .getResultList();
    }

    public List<Student> findSimilarLastName(String nameCriteria) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s where s.attendee.lastName like :nameCriteria" , Student.class);
        return query.setParameter("nameCriteria", nameCriteria)
                .getResultList();
    }

    public Optional<Student> findFirstInAlphabet(){
        TypedQuery<Student> query = em.createQuery(
                "Select s FROM Student  s ORDER BY  s.attendee.lastName ASC" , Student.class);
        return Optional.ofNullable(query.setMaxResults(1).getSingleResult());
    }

    public List<Student> find3Oldest() {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s ORDER BY age DESC" , Student.class);
        return query.setMaxResults(3).getResultList();
    }
}
