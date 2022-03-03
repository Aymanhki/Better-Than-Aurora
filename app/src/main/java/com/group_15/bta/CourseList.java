package com.group_15.bta;

import java.util.ArrayList;

public interface CourseList {

    static CourseListData getInstance() {
        return null;
    }

    ArrayList<Courses> getCourseList();

    Courses getCourses(int position);

    void insertCourses(Courses currentCourse);

    void deleteCourses(int position);
}
