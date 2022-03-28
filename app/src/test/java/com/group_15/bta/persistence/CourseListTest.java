package com.group_15.bta.persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.business.AccessCourses;

import com.group_15.bta.objects.Courses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CourseListTest implements CoursePersistence {
    private ArrayList<Courses> courses;

    public CourseListTest() {
        this.courses = new ArrayList<>();

        courses.add(new Courses("COMP3010", "Distributed Computing"));
        courses.add(new Courses("COMP3020", "Human-Computer Interaction"));
        courses.add(new Courses("COMP3350", "Software Engineering I"));
        courses.add(new Courses("COMP3380", "Databases"));
    }
    @Override
    public ArrayList<Courses> getCourseList() {
        return courses;
    }

    @Override
    public void insertCourses(Courses currentCourse) {
        // don't bother checking for duplicates
        courses.add(currentCourse);
    }

    @Override
    public void updateCourse(Courses currentCourse) {
        int index;

        index = courses.indexOf(currentCourse.getID());
        if (index >= 0)
        {
            courses.set(index, currentCourse);
        }
    }

    @Override
    public void deleteCourses(Courses currentCourse) {
        int index;

        index = courses.indexOf(currentCourse);
        if (index >= 0)
        {
            courses.remove(index);
        }
    }
}
