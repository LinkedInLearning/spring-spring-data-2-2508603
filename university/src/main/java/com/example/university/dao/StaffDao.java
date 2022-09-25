package com.example.university.dao;

import com.example.university.domain.Staff;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
/**
 * Data Access Object Class for the Staff Entity.
 * Uses only javax.persistence libraries.
 */

@Repository
public class StaffDao {
    private EntityManager em;
    public StaffDao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public List<Staff> findAll() {
        return em.createQuery("from Staff").getResultList();
    }
    public Optional<Staff> findById(int id){
        return Optional.ofNullable(em.find(Staff.class, id));
    }

    public Staff save(Staff staff) {
        em.getTransaction().begin();
        em.persist(staff);
        em.getTransaction().commit();
        return staff;
    }

    public void delete(Staff staff){
        em.getTransaction().begin();
        em.remove(staff);
        em.getTransaction().commit();
    }


    public void deleteAll() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Staff").executeUpdate();
        em.getTransaction().commit();
    }


    public List<Staff> findByLastName(String lastName) {
        TypedQuery<Staff> query = em.createQuery(
                "SELECT s FROM Staff s WHERE s.member.lastName = :lastName", Staff.class);
        return query.setParameter("lastName", lastName).getResultList();
    }

    public List<Staff> find(int pageNumber, int pageSize){
        TypedQuery<Staff> query = em.createQuery(
                "SELECT s FROM Staff s ORDER by s.member.lastName ASC", Staff.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
