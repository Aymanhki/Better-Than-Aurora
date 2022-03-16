package com.group_15.bta.persistence;

import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.objects.Courses;

import java.util.ArrayList;

public interface CoursePersistence {

    static AccessCourses getInstance() {
        return null;
    }
    ArrayList<Courses> getCourseList();
    void insertCourses(Courses currentCourse);

    void updateCourse(Courses currentCourse);

    void deleteCourses(Courses toRemove);

}
