package com.group_15.bta.objects;
import java.util.ArrayList;

/*
* Class for Category object
* used to store the name of a category for which a group of courses fall under
 */

public class Category {

    protected String Name; //category name
    protected ArrayList<com.group_15.bta.objects.Courses> Courses; //courses under this category

    //constructor
    public Category(String Name){
        this.Name = Name;
        Courses = new ArrayList<Courses>();
    }

    //getters
    public String getName() {
        return Name;
    }

    public ArrayList<Courses> getCourses(){
        return Courses;
    }
}
