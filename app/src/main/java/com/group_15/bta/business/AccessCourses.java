package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CoursePersistence;

import java.util.ArrayList;

public class AccessCourses implements CoursePersistence {
    private static final AccessCourses ourInstance = new AccessCourses();
    private CoursePersistence coursePersistence;
    public ArrayList<Courses> courses = new ArrayList<>();

    public AccessCourses() {
//        Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
//        courses.add(c);
//        c = new Courses("Comp 1020", "Introduction to Computer Science 2");
//        courses.add(c);
        coursePersistence = Services.getCoursePersistence();
    }

    public static AccessCourses getInstance() {
        return ourInstance;
    }


    public ArrayList<Courses> getCourseList() {
        return coursePersistence.getCourseList();
    }

    @Override
    public void insertCourses(Courses currentCourse) {
        coursePersistence.insertCourses(currentCourse);
    }

    @Override
    public void updateCourse(Courses currentCourse) {
        coursePersistence.updateCourse(currentCourse);
    }

    @Override
    public void deleteCourses(Courses toRemove) {
        coursePersistence.deleteCourses(toRemove);
    }


}
