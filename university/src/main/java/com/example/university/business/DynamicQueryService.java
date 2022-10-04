package com.example.university.business;

import com.example.university.dao.CourseDao;
import com.example.university.domain.Course;
import com.example.university.domain.Department;
import com.example.university.domain.Staff;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DynamicQueryService {

    private CourseDao courseDao;

    private EntityManagerFactory emf;

    private EntityManager em;

    public DynamicQueryService(CourseDao courseDao, EntityManagerFactory emf) {
        this.courseDao = courseDao;
        this.em = emf.createEntityManager();
    }

    public List<Course> findCoursesByCriteria(Optional<Department> department, Optional<Integer> credits,
                                              Optional<Staff> instructor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> root = cq.from(Course.class);
        List<Predicate> predicates = new ArrayList<>();
        department.ifPresent(d ->
                predicates.add(cb.equal(root.get("department"), d)));
        credits.ifPresent(c ->
                predicates.add(cb.equal(root.get("credits"), c)));
        instructor.ifPresent(i ->
                predicates.add(cb.equal(root.get("instructor"), i)));
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return courseDao.findByCriteria(cq);
    }

    /*
    public List<Course> filterBySpecification(CourseFilter filter) {
        Specification<Course> courseSpecification =
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    filter.getDepartment().ifPresent(d ->
                            predicates.add(criteriaBuilder.equal(root.get("department"), d)));
                    filter.getCredits().ifPresent(c ->
                            predicates.add(criteriaBuilder.equal(root.get("credits"), c)));
                    filter.getInstructor().ifPresent(i ->
                            predicates.add(criteriaBuilder.equal(root.get("instructor"), i)));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                };
        return courseRepo.findAll(courseSpecification);
    }

     */
}
