package com.example.university.domain;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Objects;

/**
 * JPA Entity representing a staff member of a department.
 *
 * Created by maryellenbowman
 */
@Entity
@Table(name="Staff_member")
public class Staff {
    @Id
    @GeneratedValue
    private Integer id;

    @Embedded
    private Person member;

    public Staff(Person member) {
        this.member = member;
    }

    protected Staff() {
    }

    public Person getMember() {
        return member;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", member=" + member +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id.equals(staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
