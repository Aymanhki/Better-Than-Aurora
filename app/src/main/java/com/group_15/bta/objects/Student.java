package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for Student object
 * used to store the student's name, as well as other details for a student (student id, password)
 */
public class Student extends User implements Serializable{


    private ArrayList<Section> enrolledSections;


    //default constructor
    public Student() {
        enrolledSections = new ArrayList<>();
    }

    //constructor (used on student side)
    public Student(String newName, String newPassword) {
        super(newName, newPassword);

        this.id = "-1";
        this.password = newPassword;
        this.name = newName;
        enrolledSections = new ArrayList<>();
    }

    //constructor (used on admin side)
    public Student(final String newID, final String newPassword, final String newStudentName) {
        this.id = newID;
        this.password = newPassword;

        this.name = newStudentName;
        enrolledSections = new ArrayList<>();

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

    public ArrayList<Section> getEnrolledSections()
    {
        return enrolledSections;
    }

    public void addSection(Section addedSection) {
        enrolledSections.add(addedSection);
    }
}


