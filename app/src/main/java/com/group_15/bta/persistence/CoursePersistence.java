package com.group_15.bta.persistence;

import com.group_15.bta.objects.Course;

import java.util.ArrayList;

public interface CoursePersistence {

    ArrayList<Course> getCourseList();

    ArrayList<Course> getCategoryCourses(String catName);
    Course getCourse(String courseID);
    void insertCourses(Course currentCourse);

    void updateCourse(Course currentCourse);

    void deleteCourses(Course toRemove);


}
