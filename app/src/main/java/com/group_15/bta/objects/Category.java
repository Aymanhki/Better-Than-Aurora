package com.group_15.bta.objects;

import java.util.ArrayList;

public class Category {
    protected String Name;
    protected ArrayList<com.group_15.bta.objects.Courses> Courses;
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
