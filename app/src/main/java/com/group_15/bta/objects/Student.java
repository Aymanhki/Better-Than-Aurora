package com.group_15.bta.objects;

import com.group_15.bta.persistence.User;

import java.util.Objects;

/*
 * Class for Student object
 * used to store the student's name, as well as other details for a student (student id, password)
 */
public class Student extends User {

    private String studentID;
    private String studentPassword;
    private String studentName;

    //default constructor
    public Student(){}

    //constructor (used to process student login)
    public Student(String newName, String newPassword) {
        super(newName, newPassword);

        this.studentID = "-1"; //id has not been set yet
        this.studentPassword = newPassword;
        this.studentName = newName;
    }

    //constructor (used to create a new student)
    public Student(final String newID, final String newPassword, final String newStudentName) {
        this.studentID = newID;
        this.studentPassword = newPassword;
        this.studentName = newStudentName;
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


    //toString
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

}


