package com.group_15.bta.objects;


import java.io.Serializable;
import java.util.ArrayList;


/*
 * Class for Category object
 * used to store the name of a category for which a group of courses fall under
 */
public class Category implements Serializable {
    protected String Name;
    protected ArrayList<com.group_15.bta.objects.Courses> Courses;
    private String coursesTableName;

    //constructor
    public Category(String Name){
        this.Name = Name; //category name
        Courses = new ArrayList<Courses>(); //courses under this category
    }

    //constructor
    public Category(String Name, ArrayList<Courses> courses)
    {
        this.Name = Name;
        this.Courses = courses;
    }

    //getters
    public String getName() {
        return Name;
    }

    public ArrayList<Courses> getCourses() {
        return Courses;
    }

    public void addCourse(Courses newCourse) {
        Courses.add(newCourse);
    }
}
