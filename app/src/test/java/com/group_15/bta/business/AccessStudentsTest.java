package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccessStudentsTest {
    private AccessStudents accessStudents;

    @Before
    public void setUp() {
        this.accessStudents = new AccessStudents(new StudentPersistenceStub());
    }

    @Test
    public void test1()
    {
        ArrayList<Student> sections = accessStudents.getStudentList();
        assertNotNull(sections);

        int currSize = sections.size();
        assertEquals("4", String.valueOf(currSize));

        Student testStudent = sections.get(0);
        ArrayList<Student> studentFound = accessStudents.getStudent(testStudent);
        assertEquals(testStudent,studentFound.get(0));

        accessStudents.insertStudent(new Student("505"));
        sections = accessStudents.getStudentList();
        currSize = sections.size();
        assertEquals("5", String.valueOf(currSize));

        accessStudents.deleteStudent(sections.get(4));
        accessStudents.deleteStudent(sections.get(3));
        sections = accessStudents.getStudentList();
        currSize = sections.size();
        assertEquals("3", String.valueOf(currSize));

    }
}