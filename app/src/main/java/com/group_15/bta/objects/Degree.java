package com.group_15.bta.objects;

import java.util.ArrayList;

public class Degree {
    private String name;
    private ArrayList<Course> courses;

    public Degree(String newName)
    {
        name = newName;
    }

    public String getName() {
        return name;
    }
}
