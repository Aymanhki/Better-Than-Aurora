package com.group_15.bta.objects;

import java.io.Serializable;

public class Section implements Serializable {
    private String section;
    private String[] Days;
    private String[] Time;
    private String instructor;
    private String location;
    private int waitListCap;
    private int available;

    public String getLocation() {
        return location;
    }

    public int getWaitListCap() {
        return waitListCap;
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

    private int CAP;

    public Section(String section, String[] Days, String[] Time, int CAP){
        this.section = section;
        this.Days = Days;
        this.Time = Time;
        this.CAP = CAP;
    }

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

    public String[] getTimes()
    {
        return Time;
    }

    public String[] getDaysRaw()
    {
        return Days;
    }
}