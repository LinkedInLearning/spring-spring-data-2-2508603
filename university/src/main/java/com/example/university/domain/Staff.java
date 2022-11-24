package com.example.university.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Mongo Document representing a staff member of a department.
 * <p>
 * Created by maryellenbowman
 */
@Document
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
