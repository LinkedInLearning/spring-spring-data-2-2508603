package com.example.university.domain;

import org.springframework.data.annotation.Id;

/**
 * Mongo Document representing a staff member of a department.
 * <p>
 * Created by maryellenbowman
 */

public class Staff {

    @Id
    private String id;

    private Person member;

    public Staff(Person member) {
        this.member = member;
    }

    public Staff() {
    }

    public String getId() {
        return id;
    }

    public Person getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", member=" + member +
                '}';
    }
}
