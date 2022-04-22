package com.group_15.bta.objects;

import androidx.annotation.NonNull;

import com.group_15.bta.application.Services;
import com.group_15.bta.persistence.StudentSectionPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class for Student object
 * used to store the student's name, as well as other details for a student (student id, password)
 */
public class Student extends User implements Serializable{


    private final StudentSectionPersistence studentSectionPersistence = Services.getStudentSectionPersistence();
    private String associatedDegree;

    //default constructor
    public Student() { }

    public Student(final String newID)
    {
        this.id = newID;
        this.name = null;
        this.password = null;
    }

    //constructor (used on student side)
    public Student(String newName, String newPassword) {
        super(newName, newPassword);
    }

    //constructor (used on admin side)
    public Student(final String newID, final String newPassword, final String newStudentName, String associatedDegree) {
        this.id = newID;
        this.password = newPassword;
        this.name = newStudentName;
        this.associatedDegree = associatedDegree;
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

    //methods to access student section database
    public ArrayList<StudentSection> getEnrolledSections() {
        return studentSectionPersistence.getStudentSectionList();
    }

    public void addSection(StudentSection addedSection) {
            studentSectionPersistence.insertSection(addedSection);
    }

    public void deleteSection(StudentSection selectedSection) {
        studentSectionPersistence.deleteSection(selectedSection);
    }

    public ArrayList<Section> getSections(boolean inProgress)
    {
        return  studentSectionPersistence.getSectionList(id, inProgress);

    }

    public ArrayList<StudentSection> getStudentSections(boolean inProgress)
    {
        return  studentSectionPersistence.getStudentSectionList(id, inProgress);

    }

    public ArrayList<Course> getCourses()
    {
        return studentSectionPersistence.getCourses(id);
    }

    public int getMAX_CLASSES(){ return 5;}

    //getter
    public String getAssociatedDegree() {
        return associatedDegree;
    }

    //toString
    @NonNull
    public String toString()
    {
        return String.format("Student: %s %s %s", id, password, name);
    }
}


