package com.example.university.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JPA Entity for a Course offered at the University.
 *
 * Created by maryellenbowman.
 */
@Entity
@Table(name="COURSE")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer credits;

    @OneToOne
    private Staff instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> prerequisites = new ArrayList<>();


    @ManyToOne
    private Department department;

    public Course(String name, Integer credits, Staff instructor, Department department) {
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
        this.department = department;
    }

    protected Course() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Staff getInstructor() {
        return instructor;
    }

    public Department getDepartment() {
        return department;
    }

    public Course addPrerequisite(Course prerequisite){
        prerequisites.add(prerequisite);
        return this;
    }

    public Integer getCredits() {
        return credits;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", credits=" + credits +
                ", instructor=" + instructor +
                ", department=" + department.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
