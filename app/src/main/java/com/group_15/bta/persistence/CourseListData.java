package com.group_15.bta.persistence;

import com.group_15.bta.objects.Courses;

import java.util.ArrayList;

/*
 * Class for List of courses ("database")
 */

public class CourseListData implements ICourseList {

    private static final CourseListData ourInstance = new CourseListData(); //one instance
    public ArrayList<Courses> courses = new ArrayList<>(); //list of courses

    //constructor
    private CourseListData() {

        //hardcoded courses into "database"
        Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
        courses.add(c);
        c = new Courses("Comp 1020", "Introduction to Computer Science 2");
        courses.add(c);
    }

    //getters
    public static CourseListData getInstance(){
        return ourInstance;
    }

    public Courses getCourses(int position){
        return courses.get(position);
    }

    public ArrayList<Courses> getCourseList(){
        return this.courses;
    }

    //add a course to the list
    public void insertCourses(Courses currentCourse){
        courses.add(currentCourse);
    }

    //delete a course from the list
    public void deleteCourses(int position){
        courses.remove(position);
    }

}
