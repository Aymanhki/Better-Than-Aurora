package com.group_15.bta.objects;

/*
 * Class for Section object
 * used to store the section name, as well as other details for a section (days, time, cap)
 */

public class Section {

    private String section; //section name
    private String[] Days; //days class section is on
    private String[] Time; //time class section is
    private int CAP; //max # of students in section

    //constructor
    public Section(String section, String[] Days, String[] Time, int CAP){
        this.section = section;
        this.Days = Days;
        this.Time = Time;
        this.CAP = CAP;
    }

    //getters
    public String getSection(){return section;}

    public String getTime(){
        return " " +Time[0] + " - " + Time[1];
    }

    public String getDays(){
        String ret = Days[0];

        for(int i = 1; i< Days.length;i++){
            ret = ret + ", " + Days[i];
        }
        return ret;
    }

    public String getCap(){
        return String.valueOf(CAP);
    }
}