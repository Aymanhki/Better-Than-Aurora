package com.group_15.bta;

import org.junit.Test;

import com.group_15.bta.objects.Student;
import static org.junit.Assert.*;

public class StudentTest
{
    @Test
    public void testStudent1()
    {
        Student student;

        System.out.println("\nStarting testStudent");

        student = new Student("123", "123", "Jo");
        assertNotNull(student);
        assertTrue("123".equals(student.getStudentID()));
        assertTrue("123".equals(student.getStudentPassword()));
        assertTrue("Jo".equals(student.getStudentName()));

        System.out.println("Finished testStudent");
    }
}