package com.group_15.bta.persistence;

import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.objects.Courses;

import java.util.ArrayList;

public interface CourseList {

    static AccessCourses getInstance() {
        return null;
    }

    ArrayList<Courses> getCourseList();

    Courses getCourses(int position);

    void insertCourses(Courses currentCourse);

    void deleteCourses(int position);
}
