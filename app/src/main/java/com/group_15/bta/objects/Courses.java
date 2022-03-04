package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Courses implements Serializable {
    private String ID;
    private String Description;
    protected ArrayList<Section> sections;
    private String title;

    public Courses(String ID, String Description){
        this.Description = Description;
        this.ID = ID;
    }
    public String getID(){ return this.ID;}

    public void addSection(String sectionNumber, String[] Days, String[] Time,int CAP){
        Section S = new Section(sectionNumber,Days,Time,CAP);
        sections.add(S);
    }

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
}