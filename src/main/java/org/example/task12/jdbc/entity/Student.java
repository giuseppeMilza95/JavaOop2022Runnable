package org.example.task12.jdbc.entity;

import org.example.task12.annotation.Mapped;
import org.example.task12.annotation.MappedClass;

@MappedClass
public class Student {

    @Mapped
    private String name;

    private String surname;
    @Mapped
    private String email;
    @Mapped
    private String gender;

    public Student(String name, String surname, String email, String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
