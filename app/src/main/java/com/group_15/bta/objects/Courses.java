package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Class for Courses object
 * used to store the course id, description, and sections that are available for this course
 */
public class Courses implements Serializable {

    private String ID; //course id
    private String Description;//course description
    protected ArrayList<Section> sections; //sections for this course
    private String title;

    //constructor
    public Courses(String ID, String Description){
        this.Description = Description;
        this.ID = ID;
    }

    //constructor
    public Courses(String ID, String title, String Description, ArrayList<Section> sections)
    {
        this.ID = ID;
        this.title = title;
        this.Description = Description;
        this.sections = sections;
    }

    //adds section to this course
    public void addSection(String sectionNumber, String[] Days, String[] Time,int CAP){
        Section S = new Section(sectionNumber,Days,Time,CAP);
        sections.add(S);
    }

    //getters
    public String getDescription() {
        return Description;
    }

    public String getTitle()
    {
        return title;
    }

    public ArrayList<Section> getSections()
    {
        return sections;
    }

    public String getID(){ return this.ID;}
}