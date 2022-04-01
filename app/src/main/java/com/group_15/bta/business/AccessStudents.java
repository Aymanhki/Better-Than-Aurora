package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.HSQLDB.PersistenceException;
import com.group_15.bta.persistence.SectionPersistence;
import com.group_15.bta.persistence.StudentPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccessStudents implements StudentPersistence {

    private static final AccessStudents ourInstance = new AccessStudents();
    public ArrayList<Student> studentList = new ArrayList<>();
    private StudentPersistence studentPersistence;
    private Student student;

    public AccessStudents() {
        studentPersistence = Services.getStudentPersistence();
    }

    public static AccessStudents getInstance() {
        return ourInstance;
    }

    public AccessStudents(final StudentPersistence studentPersistence) {
        this();
        this.studentPersistence = studentPersistence;
    }

    public ArrayList<Student> getStudent (Student getStudent) {
        student = null;
        ArrayList<Student> studentToReturn = new ArrayList<>();
        studentList = studentPersistence.getStudent(new Student(getStudent.getID()));
        if (studentList.size()==1)
        {
            student = studentList.get(0);
            studentToReturn.add(student);
        }
		return studentToReturn;
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
