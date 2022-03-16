package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Section;
import com.group_15.bta.persistence.CoursePersistence;

import java.util.ArrayList;

public class AccessCourses implements CoursePersistence {
    private static final AccessCourses ourInstance = new AccessCourses();
    private CoursePersistence coursePersistence = Services.getCoursePersistence();
    public ArrayList<Courses> courses = new ArrayList<>();

    public AccessCourses() {
//        Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
//        courses.add(c);
//        c = new Courses("Comp 1020", "Introduction to Computer Science 2");
//        courses.add(c);
        courses = coursePersistence.getCourseList();
    }

    public static AccessCourses getInstance() {
        return ourInstance;
    }


    public ArrayList<Courses> getCourseList() {
        return this.courses;
    }

    public void insertCourses(Courses currentCourse) {
        courses.add(currentCourse);
    }

    public void deleteCourses(Courses toRemove) {
        courses.remove(toRemove);
    }

}
