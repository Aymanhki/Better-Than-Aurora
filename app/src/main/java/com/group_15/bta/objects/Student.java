package com.group_15.bta.objects;

import com.group_15.bta.persistence.LogInHandler;
import com.group_15.bta.persistence.User;
import com.group_15.bta.persistence.LogInHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Student extends User implements Serializable{

    private String studentID;
    private String studentPassword;
    private ArrayList<Section> enrolledSections;
    private String studentName;

    public Student(){}

    public Student(String newName, String newPassword) {
        super(newName, newPassword);

        this.studentID = "-1";
        this.studentPassword = newPassword;
        this.studentName = newName;
        enrolledSections = new ArrayList<>();
    }

    public Student(final String newID, final String newPassword, final String newStudentName) {
        this.studentID = newID;
        this.studentPassword = newPassword;

        this.studentName = newStudentName;
        enrolledSections = new ArrayList<>();

    }


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

}


