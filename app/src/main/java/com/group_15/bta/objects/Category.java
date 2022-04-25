package com.group_15.bta.objects;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * Class for Category object
 * used to store the name of a category for which a group of courses fall under
 */
public class Category implements Serializable {
    protected String Name;
    protected ArrayList<Course> Course;

    //constructor
    public Category(String Name){
        this.Name = Name; //category name
        Course = new ArrayList<>(); //courses under this category
    }

    //constructor
    public Category(String Name, ArrayList<Course> courses)
    {
        this.Name = Name;
        this.Course = courses;
    }

    //getters
    public String getName() {
        return Name;
    }

    public ArrayList<Course> getCourses() {
        return Course;
    }

    public void addCourse(Course newCourse) {
        Course.add(newCourse);
    }

    //equals
    @Override
    public boolean equals(final Object o)
    {
        return ((Category)o).Name.equals(Name);
    }
}
