package com.group_15.bta;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.group_15.bta.persistence.CourseListData;

import com.group_15.bta.objects.Courses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CourseListTest {
    private CourseListData courseList;
    private ArrayList<Courses> courses;

    @Before
    public void setup(){
        courseList = CourseListData.getInstance();
    }

    @Test
    public void test1(){

        System.out.println("\n Starting Course List Test");
        final Courses course = new Courses("Comp 2160", "Object Orientation");

        assertNull(courses);

        courses = courseList.getCourseList();

        assertNotNull(courses);

        courseList.insertCourses(course);

        assertTrue(courses.contains(course));

        courseList.deleteCourses(courses.size()-1);


        assertFalse(courses.contains(course));

        System.out.println("\n Finished Course List Test");
    }
}
