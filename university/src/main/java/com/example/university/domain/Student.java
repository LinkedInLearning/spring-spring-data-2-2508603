package com.example.university.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JPA Entity representing a student at the University.
 *
 * Created by maryellenbowman
 */
@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue
    private Integer studentId;

    @Embedded
    private Person attendee;

    @Column
    private boolean fullTime;

    @Column
    private Integer age;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Student(Person attendee, boolean fullTime, Integer age) {
        this.attendee = attendee;
        this.fullTime = fullTime;
        this.age = age;
        courses = new ArrayList<>();
    }

    protected Student() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Person getAttendee() {
        return attendee;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public Integer getAge() {
        return age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", " + attendee +  ", fullTime=" + fullTime +
                ", age=" + age + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}
