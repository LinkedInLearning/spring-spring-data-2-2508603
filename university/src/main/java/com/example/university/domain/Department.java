package com.example.university.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Mongo Document for a Department of study at the University.
 * <p>
 * Created by maryellenbowman
 */
@Document
public class Department {
    @Id
    private String id;

    private String name;

    private Staff chair;

    public Department(String name, Staff chair) {
        this.name = name;
        this.chair = chair;
    }

    public Department() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Staff getChair() {
        return chair;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChair(Staff chair) {
        this.chair = chair;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chair=" + chair +
                '}';
    }
}
