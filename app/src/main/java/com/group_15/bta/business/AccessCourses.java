package com.group_15.bta.business;

import com.group_15.bta.application.Services;
import com.group_15.bta.objects.Course;
import com.group_15.bta.persistence.CoursePersistence;

import java.util.ArrayList;

/**
 * AccessCourses
 * Class to access courses in database.
 */
public class AccessCourses implements CoursePersistence {
    //instance of course database
    private CoursePersistence coursePersistence;

    //constructor, to start/set database
    public AccessCourses() {
        coursePersistence = Services.getCoursePersistence();
    }

    //constructor, to set database when it has already been started
   public AccessCourses(final CoursePersistence coursePersistence) {
       this();
       this.coursePersistence = coursePersistence;
   }

    /**
     * getCourseList
     * returns the courses that are in the database
     * @return an arraylist of the courses in the database
     */
    public ArrayList<Course> getCourseList() {
        return coursePersistence.getCourseList();
    }

    /**
     * insertCourses
     * inserts a course into the database
     * @param currentCourse - course to be inserted
     */
    @Override
    public void insertCourses(Course currentCourse) {
        coursePersistence.insertCourses(currentCourse);
    }

    /**
     * updateCourse
     * updates the course variables and the database, given a course
     * @param currentCourse - course to be updated
     */
    @Override
    public void updateCourse(Course currentCourse) {
        coursePersistence.updateCourse(currentCourse);
    }

    /**
     * deleteCourses
     * deletes a course from the database
     * @param toRemove - course to be deleted
     */
    @Override
    public void deleteCourses(Course toRemove) {
        coursePersistence.deleteCourses(toRemove);
    }

    /**
     * getCategoryCourses
     * given a category name, returns the courses that fall under that category that are in the database
     * @param catName - the name of the category that we want the courses to fall under
     * @return an arraylist of courses in the database that fall under that category
     */
    @Override
    public ArrayList<Course> getCategoryCourses(String catName){return coursePersistence.getCategoryCourses(catName); }

    /**
     * getCourse
     * given a course id, returns the course that has that particular id in the database
     * @param courseID - the id we want to see if is in the database, and to be returned if it is
     * @return a course object that has the id given
     */
    @Override
    public Course getCourse(String courseID) {
        return coursePersistence.getCourse(courseID);
    }


}
