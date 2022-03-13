package com.group_15.bta.business;

import com.group_15.bta.objects.Courses;
import com.group_15.bta.persistence.CourseList;

import java.util.ArrayList;

public class AccessCourses implements CourseList {
    private static final AccessCourses ourInstance = new AccessCourses();
    public ArrayList<Courses> courses = new ArrayList<>();
    private AccessCourses() {
        Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
        courses.add(c);
        c = new Courses("Comp 1020", "Introduction to Computer Science 2");
        courses.add(c);
    }

    public static AccessCourses getInstance(){
        return ourInstance;
    }

    public Courses getCourses(int position){
        return courses.get(position);
    }

    public ArrayList<Courses> getCourseList(){
        return this.courses;
    }

    public void insertCourses(Courses currentCourse){
        courses.add(currentCourse);
    }

    public void deleteCourses(int position){
        courses.remove(position);
    }

}