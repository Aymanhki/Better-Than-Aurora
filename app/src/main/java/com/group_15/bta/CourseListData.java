package com.group_15.bta;

import java.util.ArrayList;

public class CourseListData implements CourseList {
    private static final CourseListData ourInstance = new CourseListData();
    public ArrayList<Courses> courses = new ArrayList<>();
    private CourseListData () {
        Courses c = new Courses("Comp 1010", "Introduction to Computer Science");
        courses.add(c);
        c = new Courses("Comp 1020", "Introduction to Computer Science 2");
        courses.add(c);
    }

    public static CourseListData getInstance(){
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
