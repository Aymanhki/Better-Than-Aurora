package com.group_15.bta.persistence;

import java.util.ArrayList;

import com.group_15.bta.objects.Course;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CourseListTest implements CoursePersistence {
    private ArrayList<Course> courses;

    public CourseListTest() {
        this.courses = new ArrayList<>();

        courses.add(new Course("COMP3010", "Distributed Computing"));
        courses.add(new Course("COMP3020", "Human-Computer Interaction"));
        courses.add(new Course("COMP3350", "Software Engineering I"));
        courses.add(new Course("COMP3380", "Databases"));
    }
    @Override
    public ArrayList<Course> getCourseList() {
        return courses;
    }
    public ArrayList<Course> getCategoryCourses(String catName){return courses;}

    @Override
    public void insertCourses(Course currentCourse) {
        // don't bother checking for duplicates
        courses.add(currentCourse);
    }

    @Override
    public void updateCourse(Course currentCourse) {
        int index;

        index = courses.indexOf(currentCourse.getID());
        if (index >= 0)
        {
            courses.set(index, currentCourse);
        }
    }

    @Override
    public void deleteCourses(Course currentCourse) {
        int index;

        index = courses.indexOf(currentCourse);
        if (index >= 0)
        {
            courses.remove(index);
        }
    }
}
