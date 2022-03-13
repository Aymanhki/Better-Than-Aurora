package com.group_15.bta;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.objects.Student;
import com.group_15.bta.business.AccessStudents;

import com.group_15.bta.objects.Category;

import static org.junit.Assert.assertTrue;

public class StudentListTest {
    private AccessStudents studentList;
    private ArrayList<Student> students;

    @Before
    public void setUp() {
        studentList = AccessStudents.getInstance();
        students = studentList.getStudentList();
    }

    @Test
    public void test1(){
        final Category category;

        System.out.println("\n Starting Student Test");
        Student s = new Student("0","0", "Sara");

        studentList.insertStudent(s);

        students = studentList.getStudentList();

        assertTrue(students.contains(s));

        System.out.println("\n Finsihed Student List Test");
    }
}


