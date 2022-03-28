package com.group_15.bta.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class CategoryTest {
    @Test
    public void testCategory1(){
        Category cat;
        Category cat2;
        Category cat3;

        System.out.println("\nStarting Category Test");

        Course course1 = new Course("test", "test");
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);

        cat = new Category("Test Category");
        cat2 = new Category("Another Test Category");
        cat3 = new Category("Test Category with List", courses);

        assertNotNull(cat);
        assertNotNull(cat3);

        assertTrue("Test Category".equals(cat.getName()));
        cat.addCourse(course1);
        assertTrue(cat.getCourses().size() == 1);
        assertTrue(cat.getCourses().get(0).equals(course1));
        assertTrue(cat3.getCourses().size() == 1);
        assertTrue(cat3.getCourses().equals(courses));

        assertTrue(cat2.equals(cat2));
        assertFalse(cat.equals(cat2));
        assertFalse(cat.equals(cat3));
        assertTrue(cat3.equals(cat3));
        System.out.println("\nDone Category Test");
    }
}
