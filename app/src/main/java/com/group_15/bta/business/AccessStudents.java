package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.HSQLDB.PersistenceException;
import com.group_15.bta.persistence.StudentPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccessStudents implements StudentPersistence {
    private static final AccessStudents ourInstance = new AccessStudents();
    public ArrayList<Student> studentList = new ArrayList<>();
    private StudentPersistence studentPersistence;
    private AccessStudents() {
//        Student one = new Student("12", "12", "Jane Doe");
//        Student two = new Student("13", "13", "John Doe");
//        this.studentList.add(one);
//        this.studentList.add(two);
        studentPersistence = Services.getStudentPersistence();
    }

    public static AccessStudents getInstance() {
        return ourInstance;
    }

    public ArrayList<Student> getStudentList() {
        return studentPersistence.getStudentList();
    }

    @Override
    public void updateStudent(Student currentStudent) {
        studentPersistence.updateStudent(currentStudent);
    }

    public void insertStudent(Student currentStudent) {
        studentPersistence.insertStudent(currentStudent);
    }

    public void deleteStudent(Student toRemove) {
        studentPersistence.deleteStudent(toRemove);
    }
}
