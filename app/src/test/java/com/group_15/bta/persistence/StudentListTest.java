package com.group_15.bta.persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group_15.bta.persistence.StudentPersistence;
import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessSections;

import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.SectionPersistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StudentListTest implements StudentPersistence {
    private ArrayList<Student> students;

    public StudentListTest() {
        this.students = new ArrayList<>();

        students.add(new Student("101"));
        students.add(new Student("102"));
        students.add(new Student("303"));
        students.add(new Student("404"));
    }
    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }

    @Override
    public ArrayList<Student> getStudent(Student student) {
        ArrayList<Student> studentToReturn = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(student.getStudentID())) {studentToReturn.add(students.get(i));}
        }
        return studentToReturn;
    }

    @Override
    public void updateStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0) {
            students.set(index, currentStudent);
        }
    }

    @Override
    public void insertStudent(Student currentStudent) {
        // don't bother checking for duplicates
        students.add(currentStudent);
    }

    @Override
    public void deleteStudent(Student currentStudent) {
        int index;

        index = students.indexOf(currentStudent);
        if (index >= 0)
        {
            students.remove(index);
        }
    }
}
