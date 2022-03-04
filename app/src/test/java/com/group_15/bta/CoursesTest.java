package com.group_15.bta;

import org.junit.Test;

import com.group_15.bta.objects.Courses;

import static org.junit.Assert.*;

public class CoursesTest {

    @Test
    public void test(){

        Courses courses;
        System.out.println("\nStarting Course List Test");

        courses = new Courses("Comp 2160", "Object Orientation");
        assertNotNull(courses);
        assertTrue("Comp 2160".equals(courses.getID()));
        assertTrue("Object Orientation".equals(courses.getDescription()));

        System.out.println("\nDone Course List Test");

    }


}
