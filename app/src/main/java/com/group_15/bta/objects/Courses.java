package com.group_15.bta.objects;

import java.util.ArrayList;

/*
 * Class for Courses object
 * used to store the course id, description, and sections that are available for this course
 */

public class Courses {

    private String ID; //course id
    private String Description; //course description
    protected ArrayList<Section> Sections; //sections for this course

    //constructor
    public Courses(String ID, String Description){
        this.Description = Description;
        this.ID = ID;
    }

    /*
    * adds section to this course
     */
    public void addSection(String sectionNumber, String[] Days, String[] Time,int CAP){
        Section S = new Section(sectionNumber,Days,Time,CAP);
        Sections.add(S);
    }

    //getters
    public String getID(){ return this.ID;}

    public String getDescription() {
        return Description;
    }
}