package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for Student object
 * used to store the student's name, as well as other details for a student (student id, password)
 */
public class Student extends User implements Serializable{

    private String studentID;
    private String studentPassword;
    private ArrayList<Section> enrolledSections;
    private String studentName;

    //default constructor
    public Student(){}

    //constructor (used on student side)
    public Student(String newName, String newPassword) {
        super(newName, newPassword);

        this.studentID = "-1";
        this.studentPassword = newPassword;
        this.studentName = newName;
        enrolledSections = new ArrayList<>();
    }

    //constructor (used on admin side)
    public Student(final String newID, final String newPassword, final String newStudentName) {
        this.studentID = newID;
        this.studentPassword = newPassword;

        this.studentName = newStudentName;
        enrolledSections = new ArrayList<>();

    }


    //getters
    public String getStudentID()
    {
        return (studentID);
    }

    public String getStudentPassword()
    {
        return (studentPassword);
    }

    public String getStudentName()
    {
        return (studentName);
    }


    //tostring
    public String toString()
    {
        return String.format("Student: %s %s %s", studentID, studentPassword, studentName);
    }

    public int hashCode()
    {
        return Objects.hash(studentID, studentPassword, studentName);
    }

    public boolean equals(final Student o)
    {
        return Objects.equals(this.studentID, o.studentID) &&
                Objects.equals(this.studentPassword, o.studentPassword) &&
                Objects.equals(this.studentName, o.studentName);
    }

    public ArrayList<Section> getEnrolledSections()
    {
        return enrolledSections;
    }

    public void addSection(Section addedSection) {
        enrolledSections.add(addedSection);
    }
}


