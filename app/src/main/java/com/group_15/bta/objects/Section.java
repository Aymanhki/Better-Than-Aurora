package com.group_15.bta.objects;

import java.io.Serializable;

/*
 * Class for Section object
 * used to store the section name, as well as other details for a section (days, time, cap)
 */
public class Section implements Serializable {
    private String section;
    private String[] Days;
    private String[] Time;
    private String instructor;
    private String location;

    private int available;
    private int CAP;
    private String associatedCourse;
    private String associatedCategory;

    //constructor
    public Section(String section, String[] days, String[] time, int CAP) {
        this.section = section;
        Days = days;
        Time = time;
        this.CAP = CAP;
    }


    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public String getAssociatedCategory() {
        return associatedCategory;
    }

    public Section(String sectionID, String instructor, String[] days, String[] times,
                   String location, int available, int capacity,
                   String associatedCourse, String associatedCategory) {
        section = sectionID;
        this.instructor = instructor;
        Days = days;
        Time = times;
        this.location = location;
        this.available = available;
        this.CAP = capacity;
        this.associatedCourse = associatedCourse;
        this.associatedCategory = associatedCategory;
    }

    //getters
    public String getSection() {
        return section;
    }

    public String getTime() {
        return " " + Time[0] + " - " + Time[1];
    }

    public String getDays() {
        String ret = Days[0];

        for(int i = 1; i< Days.length;i++){
            ret = ret + ", " + Days[i];
        }
        return ret;
    }

    public String getCap(){
        return String.valueOf(CAP);
    }

    public String[] getTimes()
    {
        return Time;
    }

    public String[] getDaysRaw()
    {
        return Days;
    }

    public String getLocation() {
        return location;
    }


    public int getAvailable() {
        return available;
    }

    public int getCAP() {
        return CAP;
    }

    public String getInstructor() {
        return instructor;
    }
}