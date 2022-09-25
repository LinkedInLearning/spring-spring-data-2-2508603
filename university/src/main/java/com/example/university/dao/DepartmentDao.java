package com.example.university.dao;

import com.example.university.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
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

    public List<Department> findAll() {
        return em.createQuery("from Department").getResultList();
    }
    public Optional<Department> findById(int id){
        return Optional.ofNullable(em.find(Department.class, id));
    }

    public Department save(Department department) {
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
        return department;
    }

    public void delete(Department department){
        em.remove(department);
    }

    public void deleteAll() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Department").executeUpdate();
        em.getTransaction().commit();
    }

    public Optional<Department> findByName(String name) {
        TypedQuery<Department> query = em.createQuery(
                "SELECT d FROM Department d WHERE d.name = :name", Department.class);
        return Optional.ofNullable(query.setParameter("name", name).getSingleResult());
    }
}
