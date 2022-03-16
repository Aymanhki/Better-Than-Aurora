package com.group_15.bta.objects;

import com.group_15.bta.application.Services;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for Student object
 * used to store the student's name, as well as other details for a student (student id, password)
 */
public class Student extends User implements Serializable{


    private ArrayList<StudentSection> enrolledSections;
    private StudentSectionPersistence studentSectionPersistence = Services.getStudentSectionPersistence();

    //default constructor
    public Student() {
        enrolledSections = studentSectionPersistence.getSectionList();
    }

    //constructor (used on student side)
    public Student(String newName, String newPassword) {
        super(newName, newPassword);
        enrolledSections = studentSectionPersistence.getSectionList();
    }

    //constructor (used on admin side)
    public Student(final String newID, final String newPassword, final String newStudentName) {
        this.id = newID;
        this.password = newPassword;
        this.name = newStudentName;
        enrolledSections = studentSectionPersistence.getSectionList();
    }


    //getters
    public String getStudentID()
    {
        return (id);
    }

    public String getStudentPassword()
    {
        return (password);
    }

    public String getID() {
        return (name);
    }


    //tostring
    public String toString()
    {
        return String.format("Student: %s %s %s", id, password, name);
    }

    public int hashCode()
    {
        return Objects.hash(id, password, name);
    }

    public boolean equals(final Student o) {
        return Objects.equals(this.id, o.id) &&
                Objects.equals(this.password, o.password) &&
                Objects.equals(this.name, o.name);
    }

    public ArrayList<StudentSection> getEnrolledSections() {
        return studentSectionPersistence.getSectionList();
    }

    public void addSection(StudentSection addedSection) {

        enrolledSections.add(addedSection);
        studentSectionPersistence.insertSection(addedSection);
    }
}


