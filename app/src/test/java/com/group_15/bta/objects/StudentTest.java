package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        assertTrue("123".equals(student.getID()));
        assertNull(student.getPassword());


        assertEquals("Student: 123 null null", student.toString());

        student2 = new Student("1234");
        assertTrue(student.equals(student));
        assertFalse(student.equals(student2));

        assertEquals("5", String.valueOf(student.getMAX_CLASSES()));

        System.out.println("Finished testStudent");
    }
}