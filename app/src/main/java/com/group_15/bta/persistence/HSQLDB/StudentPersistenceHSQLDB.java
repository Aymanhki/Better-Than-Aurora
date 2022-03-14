package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentPersistenceHSQLDB implements StudentPersistence {
    private String dbPath;

    public StudentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public ArrayList<Student> getStudentList() {
        return null;
    }

    @Override
    public Student getStudent(int position) {
        return null;
    }

    @Override
    public void insertStudent(Student currentStudent) {

    }

    @Override
    public void deleteStudent(int position) {

    }
}
