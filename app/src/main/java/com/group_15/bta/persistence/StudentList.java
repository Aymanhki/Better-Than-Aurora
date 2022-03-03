package com.group_15.bta.persistence;

import java.util.ArrayList;
import com.group_15.bta.objects.Student;

public interface StudentList {
    static StudentListData getInstance() {
        return null;
    }

    ArrayList<Student> getStudentList();

    Student getStudent(int position);

    void insertStudent(Student currentStudent);

    void deleteStudent(int position);
}
