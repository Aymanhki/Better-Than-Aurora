package com.group_15.bta;

import java.util.ArrayList;

public class Category {
    protected String Name;
    protected ArrayList<Courses> Courses;
    public Category(String Name){
        this.Name = Name;
        Courses = new ArrayList<Courses>();
    }

    public String getName() {
        return Name;
    }
    public ArrayList<Courses> getCourses(){
        return Courses;
    }
}
