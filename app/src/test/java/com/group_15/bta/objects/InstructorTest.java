package com.group_15.bta.objects;

import org.junit.Test;

import com.group_15.bta.objects.Student;
import static org.junit.Assert.*;

public class InstructorTest
{
    @Test
    public void testInstructor1()
    {
        Instructor instructor;
        Instructor instructor2;

        System.out.println("\nStarting testInstructor");

        instructor = new Instructor("Sara","1234");
        assertNotNull(instructor);
        assertEquals("Sara",instructor.getID());
        assertEquals("1234", instructor.getPassword());

        instructor2 = new Instructor("123", "111", "Misa");
        assertEquals("123", instructor2.getID());
        assertEquals("Misa",instructor2.getName());
        assertEquals("111", instructor2.getPassword());

        assertTrue(instructor.equals(instructor));
        assertFalse(instructor.equals(instructor2));

        System.out.println("Finished testInstructor");
    }
}
