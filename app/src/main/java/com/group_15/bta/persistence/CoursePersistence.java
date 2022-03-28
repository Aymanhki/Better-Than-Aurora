package com.group_15.bta.persistence;

import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.objects.Course;

import java.util.ArrayList;

public interface CoursePersistence {

    static AccessCourses getInstance() {
        return null;
    }
    ArrayList<Course> getCourseList();

    ArrayList<Course> getCategoryCourses(String catName);

    void insertCourses(Course currentCourse);

    void updateCourse(Course currentCourse);

    void deleteCourses(Course toRemove);

}
