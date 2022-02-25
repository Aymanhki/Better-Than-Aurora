package com.group_15.bta;

public class Section {
    private String section;
    private String[] Days;
    private String[] Time;
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
}