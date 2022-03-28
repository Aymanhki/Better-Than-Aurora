package com.group_15.bta.objects;

import org.junit.Test;

import com.group_15.bta.objects.Student;
import static org.junit.Assert.*;

public class StudentTest
{
    @Test
    public void testStudent1()
    {
        Student student;
        Student student2;

        System.out.println("\nStarting testStudent");

        student = new Student("123");
        assertNotNull(student);
        assertTrue("123".equals(student.getStudentID()));
        assertNull(student.getStudentPassword());
        assertNull(student.getID());

        assertEquals("Student: 123 null null", student.toString());

        student2 = new Student("1234");
        assertTrue(student.equals(student));
        assertFalse(student.equals(student2));

        assertEquals("5", String.valueOf(student.getMAX_CLASSES()));

        System.out.println("Finished testStudent");
    }
}