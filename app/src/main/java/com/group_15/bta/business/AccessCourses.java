package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.persistence.CoursePersistence;

import java.util.ArrayList;

public class AccessCourses implements CoursePersistence {
    //private static final AccessCourses ourInstance = new AccessCourses();
    private CoursePersistence coursePersistence;
    public ArrayList<Course> courses = new ArrayList<>();

    public AccessCourses() {
        coursePersistence = Services.getCoursePersistence();
    }

   // public static AccessCourses getInstance() {return ourInstance;}
   public AccessCourses(final CoursePersistence coursePersistence) {
       this();
       this.coursePersistence = coursePersistence;
   }

    public ArrayList<Course> getCourseList() {
        return coursePersistence.getCourseList();
    }

    @Override
    public void insertCourses(Course currentCourse) {
        coursePersistence.insertCourses(currentCourse);
    }

    @Override
    public void updateCourse(Course currentCourse) {
        coursePersistence.updateCourse(currentCourse);
    }

    @Override
    public void deleteCourses(Course toRemove) {
        coursePersistence.deleteCourses(toRemove);
    }

    @Override
    public ArrayList<Course> getCategoryCourses(String catName){return coursePersistence.getCategoryCourses(catName); }

}
