package com.group_15.bta.objects;

import java.util.ArrayList;

public class Courses {
    private String ID;
    private String Description;
    protected ArrayList<Section> Sections;
    public Courses(String ID, String Description){
        this.Description = Description;
        this.ID = ID;
    }
    public String getID(){ return this.ID;}
    public void addSection(String sectionNumber, String[] Days, String[] Time,int CAP){
        Section S = new Section(sectionNumber,Days,Time,CAP);
        Sections.add(S);
    }

    public String getDescription() {
        return Description;
    }
}