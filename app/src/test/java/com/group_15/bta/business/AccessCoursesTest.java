package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Category;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.persistence.CategoryListTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.persistence.CourseListTest;

public class AccessCoursesTest {
    private AccessCourses accessCourses;

    @Before
    public void setUp() {
        this.accessCourses = new AccessCourses(new CourseListTest());
    }

    @Test
    public void test1()
    {
        ArrayList<Courses> courses = accessCourses.getCourseList();
        assertNotNull(courses);

        int currSize = courses.size();
        assertEquals("4", String.valueOf(currSize));

        accessCourses.insertCourses(new Courses("COMP4000", "Some Course"));
        courses = accessCourses.getCourseList();
        currSize = courses.size();
        assertEquals("5", String.valueOf(currSize));

        accessCourses.deleteCourses(courses.get(4));
        accessCourses.deleteCourses(courses.get(3));
        courses = accessCourses.getCourseList();
        currSize = courses.size();
        assertEquals("3", String.valueOf(currSize));

    }
}
