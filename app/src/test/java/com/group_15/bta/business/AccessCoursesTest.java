package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.group_15.bta.objects.Course;
import com.group_15.bta.persistence.CoursePersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccessCoursesTest {
    private AccessCourses accessCourses;

    @Before
    public void setUp() {
        this.accessCourses = new AccessCourses(new CoursePersistenceStub());
    }

    @Test
    public void test1()
    {
        ArrayList<Course> courses = accessCourses.getCourseList();
        assertNotNull(courses);

        int currSize = courses.size();
        assertEquals("14", String.valueOf(currSize));

        accessCourses.insertCourses(new Course("COMP4000", "Some Course"));
        courses = accessCourses.getCourseList();
        currSize = courses.size();
        assertEquals("15", String.valueOf(currSize));

        accessCourses.deleteCourses(courses.get(4));
        accessCourses.deleteCourses(courses.get(3));
        courses = accessCourses.getCourseList();
        currSize = courses.size();
        assertEquals("13", String.valueOf(currSize));

    }
}
